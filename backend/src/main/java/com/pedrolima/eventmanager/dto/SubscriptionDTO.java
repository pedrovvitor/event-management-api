package com.pedrolima.eventmanager.dto;

import java.io.Serializable;
import java.time.Instant;

import com.pedrolima.eventmanager.entities.Subscription;
import com.pedrolima.eventmanager.entities.enums.SubscriptionStatus;

public class SubscriptionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String eventId;

	private String userId;

	private Instant moment;

	private Integer status;

	private boolean isCheckedIn;

	public SubscriptionDTO() {

	}

	public SubscriptionDTO(Subscription obj) {
		id = obj.getId();
		eventId = obj.getEvent().getId();
		userId = obj.getUser().getId();
		moment = obj.getMoment();
		isCheckedIn = obj.isCheckedIn();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;

	}

	public SubscriptionStatus getStatus() {
		return SubscriptionStatus.toEnum(status);
	}

	public void setStatus(SubscriptionStatus status) {
		this.status = status.getCod();
	}

	public boolean isCheckedIn() {
		return isCheckedIn;
	}

	public void setCheckedIn(boolean isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}

}
