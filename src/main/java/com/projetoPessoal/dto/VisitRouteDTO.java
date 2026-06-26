package com.projetoPessoal.dto;

import lombok.Builder;
import java.time.LocalDate;
import java.util.List;

@Builder
public record VisitRouteDTO(
        Long id,
        String routeName,
        LocalDate creationDate,
        String status,
        List<RouteStopDTO> stops
) {}
