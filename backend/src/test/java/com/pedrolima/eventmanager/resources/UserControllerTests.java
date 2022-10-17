package com.pedrolima.eventmanager.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedrolima.eventmanager.dto.UserDTO;
import com.pedrolima.eventmanager.services.UserService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private UserService userService;
  @Autowired
  private ObjectMapper objectMapper;

  private UserDTO userDTO;
  private Page<UserDTO> page;

  @BeforeEach
  void setUp() throws Exception {
    userDTO = UserDTO.builder().id(1L).name("Pedro Lima").build();
    page = new PageImpl<>(List.of(userDTO));
  }

  @Test
  public void findAllShouldReturnPage() throws Exception {
    Mockito.when(userService.findAll(any())).thenReturn(page);
    mockMvc
        .perform(get("/users")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").exists());
  }
}
