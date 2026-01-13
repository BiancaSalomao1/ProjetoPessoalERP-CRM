package com.projetoPessoal.dto;

import java.time.LocalDateTime;

public record VisitHistoryDTO(
        Long id,
        LocalDateTime visitDate,
        String description,
        String performedBy,
        Long userId
) {}

