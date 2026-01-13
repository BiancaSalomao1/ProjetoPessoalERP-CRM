package com.projetoPessoal.service;

import com.projetoPessoal.model.Hability;
import com.projetoPessoal.repository.HabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabilityService {

    private final HabilityRepository habilityRepository;

    public Hability findOrCreateByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da habilidade não pode ser vazio");
        }

        return habilityRepository.findByName(name.trim())
                .orElseGet(() ->
                        habilityRepository.save(
                                Hability.builder()
                                        .name(name.trim())
                                        .build()
                        )
                );
    }
}
