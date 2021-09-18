package com.pedrolima.eventmanager.dto;

import java.util.Set;

public class UserSubscriptionsDTO {

	private String id;
	private String name;
	private Set<UserSubscriptionDTO> subscriptions;

	public UserSubscriptionsDTO() {
		super();
	}

	public UserSubscriptionsDTO(String id, String name, Set<UserSubscriptionDTO> subscriptions) {
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

	public Set<UserSubscriptionDTO> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<UserSubscriptionDTO> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
