package com.projetoPessoal.dto;

import lombok.Builder;

@Builder
public record RouteStopDTO(
        Long id,
        Integer stopOrder,
        String status,
        UserDTO user
) {}
