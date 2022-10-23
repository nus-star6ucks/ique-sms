package com.mtech.ique.sms.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mtech.ique.sms.model.entity.Store;
import com.mtech.ique.sms.service.QMSClient;
import com.mtech.ique.sms.service.StoreManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/stores")
public class StoreController {

  private final StoreManagementService storeManagementService;
  @Autowired private QMSClient qmsClient;

  public StoreController(StoreManagementService storeManagementService) {
    this.storeManagementService = storeManagementService;
  }

  @PostMapping
  public ResponseEntity<Object> createStore(@RequestBody Store store) {
    Store createdStore = storeManagementService.createStore(store);
    Map<String, Object> map = new HashMap<>();
    map.put("id", createdStore.getId());
    map.put("registerTime", createdStore.getRegisterTime());
    return new ResponseEntity<>(map, HttpStatus.CREATED);
  }

  @GetMapping(value = {"", "/{id}"})
  public ResponseEntity<Object> getStores(
      @PathVariable(required = false) Long id,
      @RequestParam(name = "userId", required = false) Long merchantId) {
    if (null == id) {
      List<Store> storeList =
          null == merchantId
              ? storeManagementService.getStores()
              : storeManagementService.getStores(merchantId);
      return new ResponseEntity<>(storeList, HttpStatus.OK);
    } else {
      ObjectNode storeDetailNode = storeManagementService.getStoreDetail(id);
      return null != storeDetailNode
          ? new ResponseEntity<>(storeDetailNode, HttpStatus.OK)
          : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(value = {"/list"})
  public ResponseEntity<Object> getStores() {
    return new ResponseEntity<>(storeManagementService.getStores(), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<Object> updateStore(@RequestBody Store store) {
    return new ResponseEntity<>(storeManagementService.updateStoreInfo(store), HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<Object> deleteStore(@RequestParam("id") Long id) {
    storeManagementService.deleteStore(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/start")
  public ResponseEntity<Object> startService(@RequestParam("storeId") Long storeId) {
    storeManagementService.startService(storeId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/stop")
  public ResponseEntity<Object> stopService(@RequestParam("storeId") Long storeId) {
    storeManagementService.stopService(storeId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
