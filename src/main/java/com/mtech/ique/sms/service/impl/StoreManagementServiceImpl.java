package com.mtech.ique.sms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mtech.ique.sms.model.entity.Store;
import com.mtech.ique.sms.model.enums.StoreStatus;
import com.mtech.ique.sms.repository.StoreRepository;
import com.mtech.ique.sms.service.QMSClient;
import com.mtech.ique.sms.service.StoreManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StoreManagementServiceImpl implements StoreManagementService {

  private static final ObjectMapper objectMapper = new ObjectMapper();
  private final StoreRepository storeRepository;

  private final RedisTemplate<Long, Long> redisTemplate;
  private final QMSClient qmsClient;

  public StoreManagementServiceImpl(
      StoreRepository storeRepository,
      RedisTemplate<Long, Long> redisTemplate,
      QMSClient qmsClient) {
    this.storeRepository = storeRepository;
    this.redisTemplate = redisTemplate;
    this.qmsClient = qmsClient;
  }

  @Override
  public Store createStore(Store store) {
    return storeRepository.save(store);
  }

  @Override
  public List<Store> getStores() {
    return storeRepository.findAll();
  }

  @Override
  public Optional<Store> getStore(Long storeId) {
    return storeRepository.findById(storeId);
  }

  @Override
  public ObjectNode getStoreDetail(Long storeId) {
    Optional<Store> optionalStore = getStore(storeId);
    return optionalStore
        .map(
            store -> {
              ObjectNode storeNode = objectMapper.valueToTree(store);
              storeNode.set(
                  "queuesInfo",
                  objectMapper.valueToTree(
                      store.getSeatTypes().stream()
                          .map(
                              seatType ->
                                  qmsClient.getQueueInfo(
                                      redisTemplate.opsForValue().get(seatType.getId())))
                          .collect(Collectors.toList())));
              return storeNode;
            })
        .orElseGet(() -> null);
  }

  @Override
  public Store updateStoreInfo(Store store) {
    return storeRepository.save(store);
  }

  @Override
  public void deleteStore(Long storeId) {
    storeRepository.deleteById(storeId);
  }

  @Override
  public Boolean startService(Long storeId) {
    Optional<Store> optionalStore = getStore(storeId);
    return optionalStore
        .map(
            store -> {
              if (StoreStatus.ON_SERVICE.toString().equals(store.getStatus())) {
                log.info(String.format("'%s' has been started.", store.getName()));
                return false;
              } else {
                qmsClient
                    .createQueues(store.getSeatTypes())
                    .forEach(
                        m -> {
                          Long seatTypeId = 0L;
                          Long queueId = 0L;
                          for (Map.Entry<String, Long> e : m.entrySet()) {
                            if ("seatTypeId".equals(e.getKey())) {
                              seatTypeId = e.getValue();
                            } else {
                              queueId = e.getValue();
                            }
                          }
                          redisTemplate.opsForValue().set(seatTypeId, queueId);
                        });
                store.setStatus(StoreStatus.ON_SERVICE.toString());
                updateStoreInfo(store);
              }
              return true;
            })
        .orElseGet(() -> false);
  }

  @Override
  public void stopService(Long storeId) {
    getStore(storeId)
        .ifPresent(
            store -> {
              if (!StoreStatus.STOP_SERVICE.toString().equals(store.getStatus())) {
                qmsClient.deleteQueues(
                    store.getSeatTypes().stream()
                        .map(seatType -> redisTemplate.opsForValue().getAndDelete(seatType.getId()))
                        .collect(Collectors.toList()));
                store.setStatus(StoreStatus.STOP_SERVICE.toString());
                updateStoreInfo(store);
              }
            });
  }
}
