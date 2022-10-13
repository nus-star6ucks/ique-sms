package com.mtech.ique.sms.service;

import com.mtech.ique.sms.model.dto.QueueInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${feign.server.name}", url = "${feign.client.url.qms}", path = "/queues")
public interface QMSClient {

  @GetMapping("/{queueId}")
  QueueInfo getQueueInfo(@PathVariable("queueId") Long queueId);

  @PostMapping("/start")
  Boolean createQueues();

  @PostMapping("/stop")
  Boolean deleteQueues();
}
