package com.mtech.ique.sms.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueInfo {

  private Long queueId;
  private String seatTypeName;
  private Integer waitingSize;
  private Integer estimateWaitingTime;
}
