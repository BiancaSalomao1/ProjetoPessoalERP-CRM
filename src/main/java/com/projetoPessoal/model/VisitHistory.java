package com.projetoPessoal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "visit_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VisitHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private LocalDateTime visitDate;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String performedBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public static VisitHistory create(User user, String description, String performedBy) {
        return VisitHistory.builder()
                .user(user)
                .description(description)
                .performedBy(performedBy)
                .visitDate(LocalDateTime.now())
                .build();
    }
}
