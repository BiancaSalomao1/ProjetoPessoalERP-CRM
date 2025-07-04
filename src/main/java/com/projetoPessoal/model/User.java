package com.projetoPessoal.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @EqualsAndHashCode.Include
    private String name;

    private String email;
    private String phone;
    private String address;
    private double income;
    private int numOfDependents;
    private String status;
    private String observations;

    @ManyToMany
    @JoinTable(
            name = "user_hability",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hability_id")
    )
    @Builder.Default
    private Set<Hability> habilitySet = new HashSet<>();

    public Set<Hability> getHabilitySet() {
        return habilitySet;
    }
}
