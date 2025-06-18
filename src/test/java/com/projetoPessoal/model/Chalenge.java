package com.projetoPessoal.model;


import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;


public class Chalenge {
    private long id;
    private String name;
    private String description;

    @ManyToMany
    private Set<Chalenge> chalengeSet = new HashSet<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
