package com.projetoPessoal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetoPessoal.dto.SystemUserDTO;
import com.projetoPessoal.service.SystemUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SystemUserController.class)
public class SystemUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SystemUserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void shouldGetAllSystemUsers() throws Exception {
        SystemUserDTO user = SystemUserDTO.builder().id(1L).username("admin").role("ADMIN").build();
        when(service.findAll()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/system-users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("admin"));
    }

    @Test
    @WithMockUser
    public void shouldCreateSystemUser() throws Exception {
        SystemUserDTO dto = SystemUserDTO.builder().username("helper").password("123").role("HELPER").build();
        SystemUserDTO savedDto = SystemUserDTO.builder().id(1L).username("helper").role("HELPER").build();
        
        when(service.create(any(SystemUserDTO.class))).thenReturn(savedDto);

        mockMvc.perform(post("/api/system-users")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("helper"));
    }

    @Test
    @WithMockUser
    public void shouldUpdateSystemUser() throws Exception {
        SystemUserDTO dto = SystemUserDTO.builder().username("admin_updated").role("ADMIN").build();
        SystemUserDTO updatedDto = SystemUserDTO.builder().id(1L).username("admin_updated").role("ADMIN").build();
        
        when(service.update(eq(1L), any(SystemUserDTO.class))).thenReturn(updatedDto);

        mockMvc.perform(put("/api/system-users/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin_updated"));
    }

    @Test
    @WithMockUser
    public void shouldDeleteSystemUser() throws Exception {
        mockMvc.perform(delete("/api/system-users/1").with(csrf()))
                .andExpect(status().isNoContent());
    }
}
