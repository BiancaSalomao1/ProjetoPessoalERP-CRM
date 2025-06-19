package com.projetoPessoal.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


//entidades
@Entity
@Data // Lombok gera getters, setters, toString, equals e hashCode
@NoArgsConstructor // Gera construtor vazio
@AllArgsConstructor // Gera construtor com todos os campos
@Builder // Permite usar padrão Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String phone;
    private String adress;
    private double income;
    private int numOfDependents;
    private String status;
    private String Observations;

    @ManyToMany
    @JoinTable(
            name = "user_hability",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hability_id")
    )
    private Set<Hability> habilitySet = new HashSet<>();
}
