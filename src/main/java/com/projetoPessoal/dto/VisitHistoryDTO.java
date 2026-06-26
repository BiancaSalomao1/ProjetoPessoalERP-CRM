package com.projetoPessoal.dto;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record VisitHistoryDTO(
        Long id,
        LocalDateTime visitDate,
        String description,
        String performedBy,
        UserDTO user
) {}
