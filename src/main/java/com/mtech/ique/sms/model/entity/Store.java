package com.mtech.ique.sms.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Setter
@Getter
@Entity
public class Store {

  private Long id;
  private Long merchantId;
  private String name;
  private String type;
  private List<String> phoneNumbers;
  private List<String> queues;
  private String storeStatus;
  private String description;
}
