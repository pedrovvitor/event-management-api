package com.pedrolima.eventmanager.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private Integer vacancies;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime beginDateAndTime;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime endDateAndTime;

	public EventDTO() {

	}

	public EventDTO(String id, String name, Integer vacancies, LocalDateTime beginDateAndTime,
			LocalDateTime endDateAndTime) {
		this.id = id;
		this.name = name;
		this.vacancies = vacancies;
		this.beginDateAndTime = beginDateAndTime;
		this.endDateAndTime = endDateAndTime;
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

}
