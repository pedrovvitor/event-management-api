package com.pedrolima.eventmanager.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private SubscriptionPk id = new SubscriptionPk();

	private Instant moment;

	private boolean isCheckedIn;

	public Subscription() {
		super();
	}

	public Subscription(Event event, User user, Instant moment, boolean isCheckedIn) {
		super();
		id.setEvent(event);
		id.setUser(user);
		this.moment = moment;
		this.isCheckedIn = isCheckedIn;
	}

	@JsonIgnore
	public Event getEvent() {
		return id.getEvent();
	}

	public void setEvent(Event event) {
		id.setEvent(event);
	}

	public User getUser() {
		return id.getUser();
	}

	public void setUser(User user) {
		id.setUser(user);
	}

	public SubscriptionPk getId() {
		return id;
	}

	public void setId(SubscriptionPk id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public boolean isCheckedIn() {
		return isCheckedIn;
	}

	public void setCheckedIn(boolean isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		return Objects.equals(id, other.id);
	}

}
