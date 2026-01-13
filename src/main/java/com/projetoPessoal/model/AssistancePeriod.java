package com.projetoPessoal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "assistance_period")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AssistancePeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "start_assistance_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_assistance_date")
    private LocalDate endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public boolean isActive() {
        return endDate == null;
    }
}
