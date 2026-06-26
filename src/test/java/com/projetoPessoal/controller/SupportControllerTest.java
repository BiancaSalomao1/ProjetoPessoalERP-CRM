package com.projetoPessoal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetoPessoal.dto.SupportTicketDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SupportController.class)
public class SupportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    public void shouldReceiveSupportTicket() throws Exception {
        SupportTicketDTO ticket = SupportTicketDTO.builder()
                .subject("Site UK")
                .message("Need help with maps")
                .targetEmail("biancasalomao2024@gmail.com")
                .build();

        mockMvc.perform(post("/api/support/ticket")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk())
                .andExpect(content().string("Ticket processado (servidor de e-mail não configurado)."));
    }
}
