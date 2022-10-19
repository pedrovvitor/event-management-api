package com.pedrolima.eventmanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pedrolima.eventmanager.entities.enums.SubscriptionStatus;
import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSubscriptionDTO implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long subscriptionId;
  private Instant moment;
  private SubscriptionStatus status;
  private boolean isCheckedIn;
  @JsonIgnoreProperties("subscriptions")
  private EventDTO event;
}
