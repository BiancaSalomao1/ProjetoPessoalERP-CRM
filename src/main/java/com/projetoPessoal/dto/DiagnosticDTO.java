package com.projetoPessoal.dto;

import java.time.LocalDate;

public record DiagnosticDTO(
        Long id,
        LocalDate date,
        String status,
        String summary,
        Long userId
) {}
