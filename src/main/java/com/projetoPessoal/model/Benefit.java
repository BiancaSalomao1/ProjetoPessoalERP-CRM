package com.projetoPessoal.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "benefit")
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;
    private String description;


    // @ManyToMany(mappedBy = "benefits")
    // @ToString.Exclude
    // @EqualsAndHashCode.Exclude
    // private Set<User> userSet = new HashSet<>();

}

