package com.pedrolima.eventmanager.dto;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long eventId;

	private Long userId;

	private Instant moment;

	private Integer status;

	private boolean isCheckedIn;
}
