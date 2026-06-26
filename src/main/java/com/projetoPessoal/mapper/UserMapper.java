package com.projetoPessoal.mapper;

import com.projetoPessoal.dto.AddressDTO;
import com.projetoPessoal.dto.DependentDTO;
import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.model.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
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

        AddressDTO addressDTO = null;
        if (user.getAddressEntity() != null) {
            Address addr = user.getAddressEntity();
            addressDTO = new AddressDTO(
                    addr.getId(),
                    addr.getStreet(),
                    addr.getNumber(),
                    addr.getNeighborhood(),
                    addr.getCity(),
                    addr.getState(),
                    addr.getZipCode(),
                    addr.getLatitude(),
                    addr.getLongitude()
            );
        }

        List<DependentDTO> dependentDTOs = user.getDependents() == null
                ? Collections.emptyList()
                : user.getDependents().stream()
                        .map(d -> new DependentDTO(
                                d.getId(),
                                d.getName(),
                                d.getBirthDate() != null ? d.getBirthDate().toString() : null,
                                d.getAge()))
                        .collect(Collectors.toList());

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                addressDTO,
                user.getIncome(),
                dependentDTOs,
                user.getStatus() != null ? user.getStatus().getValue() : null,
                user.getObservations(),
                user.getPhotoPath(),
                habilities);
    }
}

