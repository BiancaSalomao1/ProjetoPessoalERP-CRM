package com.projetoPessoal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "route_stops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, name = "stop_order")
    private Integer stopOrder;

    @Column(nullable = false)
    private String status; // Ex: PENDING, VISITED, CANCELLED

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_route_id", nullable = false)
    private VisitRoute visitRoute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // O assistido que será visitado
}
