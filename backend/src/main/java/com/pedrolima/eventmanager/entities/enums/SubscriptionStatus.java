package com.pedrolima.eventmanager.entities.enums;

public enum SubscriptionStatus {

  CONFIRMED(1, "Confirmed"), CANCELED(2, "Canceled"), PENDING(3, "Pending");

  private int cod;
  private String description;

  SubscriptionStatus(int cod, String description) {
    this.cod = cod;
  }

  public static SubscriptionStatus toEnum(Integer cod) {
    if (cod == null) {
      return null;
    }
    for (SubscriptionStatus x : SubscriptionStatus.values()) {
      if (cod.equals(x.getCod())) {
        return x;
      }
    }
    throw new IllegalArgumentException("Invalid Id: " + cod);
  }

  public int getCod() {
    return cod;
  }

  public void setCod(int cod) {
    this.cod = cod;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
