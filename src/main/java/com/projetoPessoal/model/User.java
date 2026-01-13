package com.projetoPessoal.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private String phone;
    private String address;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal income;

    private Integer numOfDependents;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(length = 1000)
    private String observations;

    @Column(name = "photo_path")
    private String photoPath;

    /* RELACIONAMENTOS */

    @ManyToMany
    @JoinTable(
            name = "user_hability",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hability_id")
    )
    @Builder.Default
    private Set<Hability> habilitySet = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private Set<VisitHistory> visitHistory = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private Set<AssistancePeriod> assistancePeriods = new HashSet<>();

    /* REGRAS DE DOMÍNIO */

    public void updateBasicData(
            String name,
            String email,
            String phone,
            String address,
            BigDecimal income,
            Integer numOfDependents,
            Status status,
            String observations,
            Set<Hability> habilities
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.income = income;
        this.numOfDependents = numOfDependents;
        this.status = status;
        this.observations = observations;
        this.habilitySet = habilities;
    }

    public Collection<AssistancePeriod> getAssistancePeriods() {
        return Collections.unmodifiableSet(assistancePeriods);
    }
}
