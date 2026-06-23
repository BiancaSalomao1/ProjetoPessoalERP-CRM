package com.projetoPessoal.service;

import com.projetoPessoal.dto.UserCreateDTO;
import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.dto.UserUpdateDTO;
import com.projetoPessoal.exception.UserNotFoundException;
import com.projetoPessoal.mapper.UserMapper;
import com.projetoPessoal.model.*;
import com.projetoPessoal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final HabilityService habilityService;

    /* CONSULTAS */

    public List<UserDTO> listAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /* CRIAÇÃO */

    @Transactional
    public UserDTO createUser(UserCreateDTO dto) {

        Set<Hability> habilities = resolveHabilities(dto.habilities());

        Address addr = null;
        if (dto.address() != null) {
            addr = new Address();
            addr.setStreet(dto.address());
        }

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .phone(dto.phone())
                .addressEntity(addr)
                .income(dto.income() != null
                        ? BigDecimal.valueOf(dto.income())
                        : BigDecimal.ZERO)
                .status(dto.status() != null ? dto.status() : Status.ACTIVE)
                .observations(dto.observations())
                .habilitySet(habilities)
                .build();

        // regra de domínio
        user.startAssistance(dto.startAssistanceDate());

        userRepository.save(user);

        return userMapper.toDTO(user);
    }

    /* ATUALIZAÇÃO */

    @Transactional
    public UserDTO updateUser(Long id, UserUpdateDTO dto) {

        User user = findById(id);

        Set<Hability> habilities = resolveHabilities(dto.habilities());

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
                dto.status(),
                dto.observations(),
                habilities);

        return userMapper.toDTO(userRepository.save(user));
    }

    /* AUXILIAR */

    private Set<Hability> resolveHabilities(Set<String> names) {
        if (names == null || names.isEmpty()) {
            return new HashSet<>();
        }

        return names.stream()
                .filter(name -> name != null && !name.trim().isEmpty())
                .map(habilityService::findOrCreateByName)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    public UserDTO findDTOById(Long id) {
        User user = findById(id);
        return userMapper.toDTO(user);
    }

}
