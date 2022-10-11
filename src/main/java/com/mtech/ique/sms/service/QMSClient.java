package com.mtech.ique.sms.service;

import com.mtech.ique.sms.model.dto.QueueInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ique-qms", path = "/queues")
public interface QMSClient {

  @GetMapping
  QueueInfo getQueueInfo(@RequestParam("queueId") Long queueId);

  @PostMapping("/start")
  Boolean createQueues();

  @PostMapping("/stop")
  Boolean deleteQueues();
}
