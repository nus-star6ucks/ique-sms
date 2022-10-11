package com.mtech.ique.sms.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mtech.ique.sms.model.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreManagementService {

  Store createStore(Store store);

  List<Store> getStores();

  Optional<Store> getStore(Long storeId);

  ObjectNode getStoreDetail(Long storeId);

  Store updateStoreInfo(Store store);

  void deleteStore(Long storeId);

  void startService(Long storeId);

  void stopService(Long storeId);
}
