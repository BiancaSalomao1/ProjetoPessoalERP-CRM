package com.projetoPessoal.dto;

import com.projetoPessoal.model.Status;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

public record UserCreateDTO(

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String phone,

        String address,

        @NotNull(message = "Renda é obrigatória")
        @PositiveOrZero(message = "Renda não pode ser negativa")
        Double income,

        Integer numOfDependents,

        @NotNull(message = "Status é obrigatório")
        Status status,

        String observations,

        Set<String> habilities,

        @NotNull(message = "Data de início da assistência é obrigatória")
        LocalDate startAssistanceDate
) {}
