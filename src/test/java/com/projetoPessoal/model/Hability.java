package com.projetoPessoal.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Hability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "habilitySet")
    private Set<User> userSet = new HashSet<>();

    // Construtor padrão
    public Hability() {
    }

    // Construtor com argumentos
    public Hability(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUserSet() {
        return getUserSet();
    }



    //equals e hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hability)) return false;
        Hability hability = (Hability) o;
        return Objects.equals(id, hability.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
