package com.pedrolima.eventmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
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
}
