package com.mtech.ique.sms.model.dto;

import com.mtech.ique.sms.model.entity.SeatType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueueInfo {

  private Long queueId;
  private SeatType seatType;
  private Integer waitingSize;
  private Integer estimateWaitingTime;
}
