package com.projetoPessoal.dto;

import java.time.LocalDateTime;

public record MessageDTO(
        Long id,
        String content,
        LocalDateTime submissionDate,
        String submissionType,
        String status,
        Long destinatarioId,
        Long grupoDestinatarioId
) {}
