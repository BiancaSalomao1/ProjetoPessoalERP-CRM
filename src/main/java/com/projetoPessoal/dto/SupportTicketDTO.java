package com.projetoPessoal.dto;

import lombok.Builder;

@Builder
public record SupportTicketDTO(
        String subject,
        String message,
        String targetEmail
) {}
