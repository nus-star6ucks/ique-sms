package com.mtech.ique.sms.service;

import com.mtech.ique.sms.model.dto.QueueInfo;
import com.mtech.ique.sms.model.entity.SeatType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(value = "${feign.server.name}", url = "${feign.client.url.qms}", path = "/queues")
public interface QMSClient {

  @GetMapping("/{queueId}")
  QueueInfo getQueueInfo(@PathVariable("queueId") Long queueId);

  @PostMapping("/start")
  List<Map<String, Long>> createQueues(@RequestBody List<SeatType> seatTypes);

  @PostMapping("/stop")
  void deleteQueues(@RequestBody List<Long> queueIds);
}
