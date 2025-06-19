package com.projetoPessoal.mapper;

import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.model.Hability;
import com.projetoPessoal.model.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAdress(),
                user.getIncome(),
                user.getNumOfDependents(),
                user.getStatus(),
                user.getObservations(),
                user.getHabilitySet()
                        .stream()
                        .map(Hability::getName)
                        .collect(Collectors.toSet())
        );
    }
}
