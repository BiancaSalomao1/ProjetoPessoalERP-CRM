package com.projetoPessoal.dto;

import com.projetoPessoal.model.Status;

import java.time.LocalDate;
import java.util.Set;

public record UserCreateDTO(
        String name,
        String email,
        String phone,
        String address,
        Double income,
        Integer numOfDependents,
        Status status,
        String observations,
        Set<String> habilities,
        LocalDate startAssistanceDate
) {}
