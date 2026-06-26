package com.projetoPessoal.dto;

import lombok.Builder;

@Builder
public record SystemUserDTO(
        Long id,
        String username,
        String password,
        String role
) {}
