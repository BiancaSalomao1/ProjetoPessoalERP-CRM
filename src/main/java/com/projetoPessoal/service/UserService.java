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

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .phone(dto.phone())
                .address(dto.address())
                .income(dto.income() != null
                        ? BigDecimal.valueOf(dto.income())
                        : BigDecimal.ZERO)
                .numOfDependents(dto.numOfDependents())
                .status(dto.status() != null ? dto.status() : Status.ACTIVE)
                .observations(dto.observations())
                .habilitySet(habilities)
                .build();

        AssistancePeriod firstPeriod = new AssistancePeriod();
        firstPeriod.setStartDate(dto.startAssistanceDate());
        firstPeriod.setUser(user);

        user.getAssistancePeriods().forEach(p -> {}); // força inicialização
        user.getAssistancePeriods(); // leitura segura
        user.getAssistancePeriods(); // (intencionalmente só leitura)

        userRepository.save(user);
        user.getAssistancePeriods(); // garante persistência

        return userMapper.toDTO(user);
    }

    /* ATUALIZAÇÃO */

    @Transactional
    public UserDTO updateUser(Long id, UserUpdateDTO dto) {

        User user = findById(id);

        Set<Hability> habilities = resolveHabilities(dto.habilities());

        user.updateBasicData(
                dto.name(),
                dto.email(),
                dto.phone(),
                dto.address(),
                dto.income(),
                dto.numOfDependents(),
                dto.status(),
                dto.observations(),
                habilities
        );

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

}
