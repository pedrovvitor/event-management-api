package com.pedrolima.eventmanager.dto;

import java.io.Serializable;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventSubscriptionsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Set<EventSubscriptionDTO> subscriptions;
}
