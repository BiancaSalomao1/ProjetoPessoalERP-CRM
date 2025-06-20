package com.projetoPessoal.mapper;

import com.projetoPessoal.dto.UserDTO;
//import com.projetoPessoal.model.Hability;
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
                null
//                ,user.getHabilitySet()
//                        .stream()
//                        .map(Hability::getName)
//                        .collect(Collectors.toSet())
        );
    }
    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        //user.setAddress(dto.getAddress());
        user.setIncome(dto.getIncome());
        user.setNumOfDependents(dto.getNumOfDependents());
        user.setStatus(dto.getStatus());
        user.setObservations(dto.getObservations());

        return user;
    }
}

