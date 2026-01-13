package com.projetoPessoal.mapper;

import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.model.Hability;
import com.projetoPessoal.model.Status;
import com.projetoPessoal.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    /* ENTITY → DTO  */

    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        Set<String> habilities = user.getHabilitySet() == null
                ? Collections.emptySet()
                : user.getHabilitySet().stream()
                .filter(h -> h != null && h.getName() != null)
                .map(Hability::getName)
                .collect(Collectors.toSet());

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getIncome(),
                user.getNumOfDependents(),
                user.getStatus() != null ? user.getStatus().name() : null,
                user.getObservations(),
                habilities
        );
    }

    /*  DTO → ENTITY */

    public User toEntity(UserDTO dto, Set<Hability> habilities) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .phone(dto.phone())
                .address(dto.address())
                .income(dto.income())
                .numOfDependents(dto.numOfDependents())
                .status(dto.status() != null ? Status.valueOf(dto.status()) : null)
                .observations(dto.observations())
                .habilitySet(habilities)
                .build();
    }

    /* UPDATE PARCIAL  */

    public void updateEntity(User user, UserDTO dto, Set<Hability> habilities) {
        user.updateBasicData(
                dto.name(),
                dto.email(),
                dto.phone(),
                dto.address(),
                dto.income(),
                dto.numOfDependents(),
                dto.status() != null ? Status.valueOf(dto.status()) : null,
                dto.observations(),
                habilities
        );
    }

}
