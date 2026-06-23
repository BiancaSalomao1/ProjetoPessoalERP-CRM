package com.projetoPessoal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "visit_routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VisitRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String routeName;

    @Column(nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false)
    private String status; // Ex: PENDING, IN_PROGRESS, COMPLETED

    @OneToMany(mappedBy = "visitRoute", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RouteStop> stops = new ArrayList<>();

    public void addStop(RouteStop stop) {
        stops.add(stop);
        stop.setVisitRoute(this);
    }
}
