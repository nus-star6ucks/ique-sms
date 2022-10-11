package com.mtech.ique.sms.model.enums;

public enum StoreStatus {
  ON_SERVICE("onService"),
  STOP_SERVICE("stopService");

  private final String status;

  StoreStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return status;
  }
}
