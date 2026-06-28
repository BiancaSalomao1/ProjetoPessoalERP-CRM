package com.projetoPessoal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.dto.VisitHistoryDTO;
import com.projetoPessoal.service.VisitHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VisitHistoryController.class)
public class VisitHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitHistoryService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void shouldGetAllHistory() throws Exception {
        UserDTO userDto = new UserDTO(1L, "John", null, null, null, null, null, null, null, null, null, null, null);
        VisitHistoryDTO history = VisitHistoryDTO.builder().id(1L).visitDate(LocalDateTime.now()).description("Regular visit").performedBy("Agent A").user(userDto).build();
        when(service.listAll()).thenReturn(List.of(history));

        mockMvc.perform(get("/api/visit-history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Regular visit"));
    }

    @Test
    @WithMockUser
    public void shouldCreateHistory() throws Exception {
        UserDTO userDto = new UserDTO(1L, "John", null, null, null, null, null, null, null, null, null, null, null);
        VisitHistoryDTO dto = VisitHistoryDTO.builder().visitDate(LocalDateTime.now()).description("Regular visit").performedBy("Agent A").user(userDto).build();
        VisitHistoryDTO savedDto = VisitHistoryDTO.builder().id(1L).visitDate(LocalDateTime.now()).description("Regular visit").performedBy("Agent A").user(userDto).build();

        when(service.registerVisit(any(VisitHistoryDTO.class))).thenReturn(savedDto);

        mockMvc.perform(post("/api/visit-history")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }
}
