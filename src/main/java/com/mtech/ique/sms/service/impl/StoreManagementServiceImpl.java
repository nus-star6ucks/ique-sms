package com.mtech.ique.sms.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mtech.ique.sms.model.entity.Store;
import com.mtech.ique.sms.repository.StoreRepository;
import com.mtech.ique.sms.service.QMSClient;
import com.mtech.ique.sms.service.StoreManagementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreManagementServiceImpl implements StoreManagementService {

  private final StoreRepository storeRepository;
  private final QMSClient qmsClient;

  public StoreManagementServiceImpl(StoreRepository storeRepository, QMSClient qmsClient) {
    this.storeRepository = storeRepository;
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
    if (optionalStore.isPresent()) {
      //      qmsClient.getQueueInfo();
    }
    return null;
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
  public void startService(Long storeId) {}

  @Override
  public void stopService(Long storeId) {}
}
