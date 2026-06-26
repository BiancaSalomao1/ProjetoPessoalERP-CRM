package com.projetoPessoal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetoPessoal.dto.VisitRouteDTO;
import com.projetoPessoal.service.VisitRouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VisitRouteController.class)
public class VisitRouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitRouteService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void shouldGetAllRoutes() throws Exception {
        VisitRouteDTO route = VisitRouteDTO.builder().id(1L).routeName("Route South").creationDate(LocalDate.now()).status("PENDING").stops(new ArrayList<>()).build();
        when(service.findAll()).thenReturn(List.of(route));

        mockMvc.perform(get("/api/visit-routes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].routeName").value("Route South"));
    }

    @Test
    @WithMockUser
    public void shouldCreateRoute() throws Exception {
        VisitRouteDTO dto = VisitRouteDTO.builder().routeName("Route South").status("PENDING").stops(new ArrayList<>()).build();
        VisitRouteDTO savedDto = VisitRouteDTO.builder().id(1L).routeName("Route South").status("PENDING").stops(new ArrayList<>()).build();

        when(service.create(any(VisitRouteDTO.class))).thenReturn(savedDto);

        mockMvc.perform(post("/api/visit-routes")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }
}
