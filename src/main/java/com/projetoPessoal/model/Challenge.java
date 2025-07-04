package com.projetoPessoal.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "challenge")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;
    private String description;


//    @ManyToMany(mappedBy = "challenges")
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private Set<User> userSet = new HashSet<>();

}

