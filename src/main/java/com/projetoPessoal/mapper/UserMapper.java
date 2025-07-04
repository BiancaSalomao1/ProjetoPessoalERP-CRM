package com.projetoPessoal.mapper;

import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.model.Hability;
import com.projetoPessoal.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

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
                .build();

        if (user.getHabilitySet() != null) {
            dto.setHabilities(user.getHabilitySet().stream()
                    .map(Hability::getName)
                    .collect(Collectors.toSet()));
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
                .build();



        if (dto.getHabilities() != null) {
            user.setHabilitySet(dto.getHabilities().stream()
                    .map(name -> Hability.builder().name(name).build())
                    .collect(Collectors.toSet()));
        }

        return user;
    }
}