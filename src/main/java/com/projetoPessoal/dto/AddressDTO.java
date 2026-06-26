package com.projetoPessoal.dto;

public record AddressDTO(
        Long id,
        String street,
        String number,
        String neighborhood,
        String city,
        String state,
        String zipCode,
        Double latitude,
        Double longitude
) {}
