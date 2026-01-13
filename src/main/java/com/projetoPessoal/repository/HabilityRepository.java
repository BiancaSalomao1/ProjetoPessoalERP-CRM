package com.projetoPessoal.repository;

import com.projetoPessoal.model.Hability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabilityRepository extends JpaRepository<Hability, Long> {

    Optional<Hability> findByName(String name);
}

