package com.projetoPessoal.repository;

import com.projetoPessoal.model.VisitRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRouteRepository extends JpaRepository<VisitRoute, Long> {
}
