package com.pedrolima.eventmanager.dto;

import java.util.Set;

public class EventSubscriptionsDTO {

	private String id;
	private String name;
	private Set<EventSubscriptionDTO> subscriptions;

	public EventSubscriptionsDTO() {
		super();
	}

	public EventSubscriptionsDTO(String id, String name, Set<EventSubscriptionDTO> subscriptions) {
		super();
		this.id = id;
		this.name = name;
		this.subscriptions = subscriptions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<EventSubscriptionDTO> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<EventSubscriptionDTO> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
