package com.pedrolima.eventmanager.dto;

import java.util.Set;

public class UserSubscriptionsDTO {

	private Long id;
	private String name;
	private Set<UserSubscriptionDTO> subscriptions;

	public UserSubscriptionsDTO() {
		super();
	}

	public UserSubscriptionsDTO(Long id, String name, Set<UserSubscriptionDTO> subscriptions) {
		super();
		this.id = id;
		this.name = name;
		this.subscriptions = subscriptions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
