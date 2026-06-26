package com.projetoPessoal.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    ACTIVE("ATIVO"), 
    INACTIVE("INATIVO"),
    PENDING("PENDENTE");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static Status fromValue(String value) {
        if (value == null) return null;
        String upperValue = value.trim().toUpperCase();
        if (upperValue.equals("ATIVO") || upperValue.equals("ACTIVE")) {
            return ACTIVE;
        }
        if (upperValue.equals("INATIVO") || upperValue.equals("INACTIVE")) {
            return INACTIVE;
        }
        if (upperValue.equals("PENDENTE") || upperValue.equals("PENDING")) {
            return PENDING;
        }
        throw new IllegalArgumentException("Status desconhecido: " + value);
    }
}
