package com.projetoPessoal.repository;

import com.projetoPessoal.model.VisitHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitHistoryRepository extends JpaRepository<VisitHistory, Long> {

    List<VisitHistory> findByUserId(Long userId);
}
