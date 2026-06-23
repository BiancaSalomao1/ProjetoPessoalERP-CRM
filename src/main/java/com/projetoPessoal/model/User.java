package com.projetoPessoal.model;

import com.projetoPessoal.exception.BusinessException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address addressEntity;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal income;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Dependent> dependents = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(length = 1000)
    private String observations;

    @Column(name = "photo_path")
    private String photoPath;

    /* RELACIONAMENTOS */

    @ManyToMany
    @JoinTable(name = "user_hability", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "hability_id"))
    @Builder.Default
    private Set<Hability> habilitySet = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<VisitHistory> visitHistory = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<AssistancePeriod> assistancePeriods = new HashSet<>();

    /* REGRAS DE DOMÍNIO */

    public void updateBasicData(
            String name,
            String email,
            String phone,
            Address addressEntity,
            BigDecimal income,
            Status status,
            String observations,
            Set<Hability> habilities) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.addressEntity = addressEntity;
        this.income = income;
        this.status = status;
        this.observations = observations;
        this.habilitySet = habilities;
    }

    public Collection<AssistancePeriod> getAssistancePeriods() {
        return Collections.unmodifiableSet(assistancePeriods);
    }

    public void startAssistance(LocalDate startDate) {

        if (this.status == Status.INACTIVE) {
            throw new BusinessException("Usuário inativo não pode receber novo plano");
        }

        boolean hasActivePeriod = assistancePeriods.stream()
                .anyMatch(AssistancePeriod::isActive);

        if (hasActivePeriod) {
            throw new IllegalStateException("Usuário já possui assistência ativa");
        }

        AssistancePeriod period = new AssistancePeriod();
        period.setStartDate(startDate);
        period.setUser(this);

        this.assistancePeriods.add(period);
    }

}
