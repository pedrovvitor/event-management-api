package com.pedrolima.eventmanager.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private LocalDateTime beginDateAndTime;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime endDateAndTime;

	@OneToMany(mappedBy = "id.event")
	private Set<Subscription> subscriptions = new HashSet<>();

	public Event() {

	}

	public Event(Long id, String name, Integer vacancies, LocalDateTime beginDateAndTime,
			LocalDateTime endDateAndTime) {
		this.id = id;
		this.name = name;
		this.vacancies = vacancies;
		this.beginDateAndTime = beginDateAndTime;
		this.endDateAndTime = endDateAndTime;
	}
	
	//Listar os inscritos de um evento;
	public List<User> getUsers() {
		List<User> list = new ArrayList<>();
		for(Subscription sub : subscriptions) {
			list.add(sub.getUser());
		}
		return list;
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

	public LocalDateTime getBeginDateAndTime() {
		return beginDateAndTime;
	}

	public void setBeginDateAndTime(LocalDateTime beginDateAndTime) {
		this.beginDateAndTime = beginDateAndTime;
	}

	public LocalDateTime getEndDateAndTime() {
		return endDateAndTime;
	}

	public void setEndDateAndTime(LocalDateTime endDateAndTime) {
		this.endDateAndTime = endDateAndTime;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
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
