package com.pedrolima.eventmanager.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSubscriptionsDTO implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long id;
  private String name;
  private Page<UserSubscriptionDTO> subscriptions;
}
