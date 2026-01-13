package com.projetoPessoal.repository;

import com.projetoPessoal.model.ActionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionPlanRepository extends JpaRepository<ActionPlan, Long> {

    List<ActionPlan> findByUserId(Long userId);
}
