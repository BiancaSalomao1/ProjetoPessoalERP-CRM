package com.projetoPessoal.dto;

import java.util.Set;

public record GroupDTO(
        Long id,
        String name,
        String description,
        String type,
        Set<Long> memberIds
) {}
