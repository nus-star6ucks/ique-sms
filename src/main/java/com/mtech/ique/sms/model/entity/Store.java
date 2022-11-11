package com.mtech.ique.sms.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Store {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long merchantId;
  private String name;
  private String type;
  private String address;

  @ElementCollection private List<String> phoneNumbers;

  @ElementCollection private List<SeatType> seatTypes;

  private String status;

  @Embedded private StoreResources resources;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private Long registerTime;
}
