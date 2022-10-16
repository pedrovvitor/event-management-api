package com.pedrolima.eventmanager.entities;

import com.pedrolima.eventmanager.entities.enums.SubscriptionStatus;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_subscriptions")
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Event event;

	@ManyToOne
	private User user;

	private Instant moment;

	private Integer status;

	private boolean isCheckedIn;

	public void setStatus(SubscriptionStatus status) {
		this.status = status.getCod();
	}

	public SubscriptionStatus getStatus() {
		return SubscriptionStatus.toEnum(status);
	}
}
