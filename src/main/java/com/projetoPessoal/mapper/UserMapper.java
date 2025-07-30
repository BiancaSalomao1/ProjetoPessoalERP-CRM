package com.projetoPessoal.mapper;

import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.model.Hability;
import com.projetoPessoal.model.User;
import com.projetoPessoal.service.HabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final HabilityService habilityService;

    @Autowired
    public UserMapper(HabilityService habilityService) {
        this.habilityService = habilityService;
    }

    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .income(user.getIncome())
                .numOfDependents(user.getNumOfDependents())
                .status(user.getStatus())
                .observations(user.getObservations())
                .photo(user.getPhoto())
                .build();

        // Verificação de segurança para habilidades
        if (user.getHabilitySet() != null && !user.getHabilitySet().isEmpty()) {
            dto.setHabilities(user.getHabilitySet().stream()
                    .filter(hability -> hability != null && hability.getName() != null)
                    .map(Hability::getName)
                    .collect(Collectors.toSet()));
        } else {
            dto.setHabilities(new HashSet<>());
        }

        return dto;
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .income(dto.getIncome())
                .numOfDependents(dto.getNumOfDependents())
                .status(dto.getStatus())
                .observations(dto.getObservations())
                .photo(dto.getPhoto())
                .build();

        // Verificação de segurança para habilidades
        if (dto.getHabilities() != null && !dto.getHabilities().isEmpty()) {
            user.setHabilitySet(dto.getHabilities().stream()
                    .filter(habilityName -> habilityName != null && !habilityName.trim().isEmpty())
                    .map(habilityService::findOrCreateByName)
                    .filter(hability -> hability != null)
                    .collect(Collectors.toSet()));
        } else {
            user.setHabilitySet(new HashSet<>());
        }

        return user;
    }
}