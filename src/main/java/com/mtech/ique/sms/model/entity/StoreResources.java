package com.mtech.ique.sms.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class StoreResources {
  private String description;
  private String imageUrl;
  private Integer ratings;
}
