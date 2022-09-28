package com.mtech.ique.sms.service;

import com.mtech.ique.sms.model.entity.Store;

import java.util.List;

public interface StoreManagementService {

  Store createStore(Store store);

  List<Store> getStores(Long merchantId);

  Store updateStoreInfo(Store store);

  void deleteStore(Long storeId);

  void startService(Long storeId);

  void stopService(Long storeId);
}
