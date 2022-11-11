package com.mtech.ique.sms.repository;

import com.mtech.ique.sms.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
  List<Store> findAllByMerchantId(Long merchantId);
}
