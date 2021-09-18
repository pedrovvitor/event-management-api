package com.pedrolima.eventmanager.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_events")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer vacancies;

	@Column(nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime beginDateTime;

	@Column(nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime endDateTime;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
	private Set<Subscription> subscriptions = new HashSet<>();

	public Event() {

	}

	public Event(String name, Integer vacancies, LocalDateTime beginDateAndTime, LocalDateTime endDateAndTime) {
		this.name = name;
		this.vacancies = vacancies;
		this.beginDateTime = beginDateAndTime;
		this.endDateTime = endDateAndTime;
	}

	public Event(Long id, String name, Integer vacancies, LocalDateTime beginDateTime,
			LocalDateTime endDateAndTime) {
		this.id = id;
		this.name = name;
		this.vacancies = vacancies;
		this.beginDateTime = beginDateTime;
		this.endDateTime = endDateAndTime;
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

	public Integer getVacancies() {
		return vacancies;
	}

	public void setVacancies(Integer vacancies) {
		this.vacancies = vacancies;
	}

	public LocalDateTime getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(LocalDateTime beginDateAndTime) {
		this.beginDateTime = beginDateAndTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateAndTime) {
		this.endDateTime = endDateAndTime;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
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
		Event other = (Event) obj;
		return Objects.equals(id, other.id);
	}

}
