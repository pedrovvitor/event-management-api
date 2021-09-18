package com.pedrolima.eventmanager.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.pedrolima.eventmanager.entities.enums.SubscriptionStatus;

@Entity
@Table(name = "tb_subscriptions")
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@ManyToOne(cascade = CascadeType.MERGE)
	//@JsonIgnoreProperties("subscriptions")
	private Event event;

	@ManyToOne
	//@JsonIgnoreProperties("subscriptions")
	private User user;

	private Instant moment;

	private Integer status;

	private boolean isCheckedIn;

	public Subscription() {

	}

	public Subscription(Event event, User user, Instant moment, Integer status, boolean isCheckedIn) {
		this.event = event;
		this.user = user;
		this.moment = moment;
		this.status = status;
		this.isCheckedIn = isCheckedIn;
	}

	public Subscription(String id, Event event, User user, Instant moment,Integer status, boolean isCheckedIn) {
		this.id = id;
		this.event = event;
		this.user = user;
		this.moment = moment;
		this.status = status;
		this.isCheckedIn = isCheckedIn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		if(status != null) {
		this.status = status.getCod();
		}
	}

	public boolean isCheckedIn() {
		return isCheckedIn;
	}

	public void setCheckedIn(boolean isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(event, user);
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
		return Objects.equals(event, other.event) && Objects.equals(user, other.user);
	}

}
