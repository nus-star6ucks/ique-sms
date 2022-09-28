package com.mtech.ique.sms.model.enums;

public enum StoreStatus {
  ACTIVATED("activated"),
  PENDING("pending"),
  CANCELLED("cancelled");

  private final String status;

  StoreStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return status;
  }
}
