package com.pedrolima.eventmanager.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
  private static final String USER_PATH = "/users";

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void findAllShouldReturnPage() throws Exception {
    this.mockMvc
        .perform(get(USER_PATH)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").exists());
  }

  @Test
  public void findByIdShouldReturnUserWhenIdExists() throws Exception {
    long existentId = 1L;
    mockMvc.perform(get(USER_PATH.concat("/{id}"),existentId)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").exists());
  }

  @Test
  public void findByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
    long inexistentId = -1L;
    mockMvc.perform(get(USER_PATH.concat("/{id}"), inexistentId)
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
  }

  @Test
  public void findAllUserSubscriptionsShouldReturnPageWhenIdExists() throws Exception {
    long existentId = 1L;
    mockMvc.perform(get(USER_PATH.concat("/{id}/subscriptions"),existentId)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.subscriptions").exists())
        .andExpect(jsonPath("$.subscriptions.content").exists());
  }

  @Test
  public void findAllUserSubscriptionsShouldReturnNoFoundWhenIdDoesNotExists() throws Exception {
    long nonExistentId = -1L;
    mockMvc.perform(get(USER_PATH.concat("/{id}/subscriptions"),nonExistentId)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").exists())
        .andExpect(jsonPath("$.message").value("User not found for Id: " + nonExistentId));
  }
}
