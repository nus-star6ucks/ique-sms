package com.mtech.ique.sms.service.impl;

import com.mtech.ique.sms.model.entity.Store;
import com.mtech.ique.sms.service.StoreManagementService;

import java.util.List;

public class StoreManagementServiceImpl implements StoreManagementService {
  @Override
  public Store createStore(Store store) {
    return null;
  }

  @Override
  public List<Store> getStores(Long merchantId) {
    return null;
  }

  @Override
  public Store updateStoreInfo(Store store) {
    return null;
  }

  @Override
  public void deleteStore(Long storeId) {}

  @Override
  public void startService(Long storeId) {}

  @Override
  public void stopService(Long storeId) {}
}
