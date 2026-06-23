package com.projetoPessoal.mapper;

import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.model.Address;
import com.projetoPessoal.model.Hability;
import com.projetoPessoal.model.Status;
import com.projetoPessoal.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    /* ENTITY → DTO */

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
                user.getAddressEntity() != null ? user.getAddressEntity().getStreet() : null,
                user.getIncome(),
                user.getDependents() != null ? user.getDependents().size() : 0,
                user.getStatus() != null ? user.getStatus().name() : null,
                user.getObservations(),
                habilities);
    }

    /* DTO → ENTITY */

    public User toEntity(UserDTO dto, Set<Hability> habilities) {
        if (dto == null) {
            return null;
        }

        Address addr = null;
        if (dto.address() != null) {
            addr = new Address();
            addr.setStreet(dto.address());
        }

        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .phone(dto.phone())
                .addressEntity(addr)
                .income(dto.income())
                .status(dto.status() != null ? Status.valueOf(dto.status()) : null)
                .observations(dto.observations())
                .habilitySet(habilities)
                .build();
    }

    /* UPDATE PARCIAL */

    public void updateEntity(User user, UserDTO dto, Set<Hability> habilities) {
        Address addr = user.getAddressEntity();
        if (addr == null && dto.address() != null) {
            addr = new Address();
            addr.setStreet(dto.address());
            addr.setUser(user);
        } else if (addr != null && dto.address() != null) {
            addr.setStreet(dto.address());
        }

        user.updateBasicData(
                dto.name(),
                dto.email(),
                dto.phone(),
                addr,
                dto.income(),
                dto.status() != null ? Status.valueOf(dto.status()) : null,
                dto.observations(),
                habilities);
    }

}
