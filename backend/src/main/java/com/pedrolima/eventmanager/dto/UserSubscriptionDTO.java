package com.pedrolima.eventmanager.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pedrolima.eventmanager.entities.enums.SubscriptionStatus;

public class UserSubscriptionDTO {

	private String subscriptionId;
	private Instant moment;
	private SubscriptionStatus status;
	private boolean isCheckedIn;
	@JsonIgnoreProperties("subscriptions")
	private EventDTO event;

	public UserSubscriptionDTO() {
		super();
	}

	public UserSubscriptionDTO(String subscriptionId, Instant moment, SubscriptionStatus status, boolean isCheckedIn,
			EventDTO event) {
		super();
		this.subscriptionId = subscriptionId;
		this.moment = moment;
		this.status = status;
		this.isCheckedIn = isCheckedIn;
		this.event = event;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public SubscriptionStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionStatus subscriptionStatus) {
		this.status = subscriptionStatus;
	}

	public boolean isCheckedIn() {
		return isCheckedIn;
	}

	public void setCheckedIn(boolean isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}

	public EventDTO getEvent() {
		return event;
	}

	public void setEvent(EventDTO event) {
		this.event = event;
	}

}
