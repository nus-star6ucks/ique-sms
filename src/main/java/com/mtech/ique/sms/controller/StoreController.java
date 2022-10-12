package com.mtech.ique.sms.controller;

import com.mtech.ique.sms.model.entity.Store;
import com.mtech.ique.sms.service.QMSClient;
import com.mtech.ique.sms.service.StoreManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path = "/stores")
public class StoreController {

  private final StoreManagementService storeManagementService;
  //  @Autowired private DiscoveryClient discoveryClient;
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
  public ResponseEntity<Object> getStores(@PathVariable(required = false) Long id) {
    if (null == id) {
      return new ResponseEntity<>(storeManagementService.getStores(), HttpStatus.OK);
    } else {
      Optional<Store> storeOptional = storeManagementService.getStore(id);
      // Todo: call the getQueue info from qms.
      return storeOptional
          .<ResponseEntity<Object>>map(store -> new ResponseEntity<>(store, HttpStatus.OK))
          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
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
  public ResponseEntity<Object> startService() {
    return null;
  }

  @PostMapping("/stop")
  public ResponseEntity<Object> stopService() {
    return null;
  }
  //
  //  // Todo: just for testing the k8s discovery client.
  //  @GetMapping(value = {"/test", "/test/{id}"})
  //  public ResponseEntity<Object> getServices(@PathVariable(required = false) String instanceId) {
  //    if (null != instanceId) {
  //      return new ResponseEntity<>(discoveryClient.getInstances(instanceId), HttpStatus.OK);
  //    }
  //    return new ResponseEntity<>(discoveryClient.getServices(), HttpStatus.OK);
  //  }

  @GetMapping("/feign/{id}")
  public ResponseEntity<Object> getQueueInfo(@PathVariable Long id) {
    return new ResponseEntity<>(qmsClient.getQueueInfo(id), HttpStatus.OK);
  }
}
