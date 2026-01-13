package com.projetoPessoal.dto;

import java.time.LocalDate;

public record ActionPlanDTO(
        Long id,
        String description,
        LocalDate deadline,
        Boolean fulfilled,
        Long userId
) {}
