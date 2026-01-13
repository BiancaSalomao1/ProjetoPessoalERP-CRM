package com.projetoPessoal.dto;

import com.projetoPessoal.model.Status;

import java.math.BigDecimal;
import java.util.Set;

public record UserUpdateDTO(
        String name,
        String email,
        String phone,
        String address,
        BigDecimal income,
        Integer numOfDependents,
        Status status,
        String observations,
        Set<String> habilities
) {}
