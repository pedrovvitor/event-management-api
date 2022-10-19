package com.pedrolima.eventmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventDTO {

  private Long id;
  private String name;
  private Integer vacancies;
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime beginDateAndTime;
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime endDateAndTime;
}
