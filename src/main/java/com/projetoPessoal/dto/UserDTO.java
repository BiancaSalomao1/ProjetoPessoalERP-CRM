package com.projetoPessoal.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record UserDTO(
        Long id,
        String name,
        String email,
        String phone,
        AddressDTO addressEntity,
        BigDecimal income,
        List<DependentDTO> dependents,
        String status,
        String observations,
        String photoPath,
        Set<String> habilities,
        java.time.LocalDate startAssistanceDate,
        java.time.LocalDate endAssistanceDate
) {}
