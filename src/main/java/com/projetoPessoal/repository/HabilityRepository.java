package com.projetoPessoal.repository;

import com.projetoPessoal.model.Hability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Indica que esta interface é um componente de repositório do Spring.
public interface HabilityRepository extends JpaRepository<Hability, Long> {

    /*
     Busca uma Habilidade pelo seu nome.
     pode "return" um opcional contendo a Hability encontrada, ou um Optional vazio se nenhuma habilidade for encontrada.
     */
    Optional<Hability> findByName(String name);
    boolean existsByName(String name);
}

