package com.projetoPessoal.service;

import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.exception.UserNotFoundException;
import com.projetoPessoal.mapper.UserMapper;
import com.projetoPessoal.model.*;
import com.projetoPessoal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final HabilityService habilityService;

    /* CONSULTAS */

    @Transactional(readOnly = true)
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
    public UserDTO createUser(UserDTO dto) {

        Set<Hability> habilities = resolveHabilities(dto.habilities());

        Address addr = buildAddress(dto);

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .phone(dto.phone())
                .addressEntity(addr)
                .income(dto.income() != null ? dto.income() : BigDecimal.ZERO)
                .status(dto.status() != null ? Status.fromValue(dto.status()) : Status.ACTIVE)
                .observations(dto.observations())
                .photoPath(dto.photoPath())
                .habilitySet(habilities)
                .build();

        // Adicionar dependentes
        if (dto.dependents() != null) {
            for (var depDto : dto.dependents()) {
                Dependent dep = new Dependent();
                dep.setName(depDto.name());
                if (depDto.birthDate() != null && !depDto.birthDate().isEmpty()) {
                    dep.setBirthDate(LocalDate.parse(depDto.birthDate()));
                }
                dep.setUser(user);
                user.getDependents().add(dep);
            }
        }

        // Inicia assistência com a data informada no DTO ou a data de hoje
        try {
            LocalDate startDate = dto.startAssistanceDate() != null ? dto.startAssistanceDate() : LocalDate.now();
            user.startAssistance(startDate);
        } catch (Exception e) {
            // Se falhar (ex: usuário INATIVO), ignorar silenciosamente
        }

        userRepository.save(user);

        return userMapper.toDTO(user);
    }

    /* ATUALIZAÇÃO */

    @Transactional
    public UserDTO updateUser(Long id, UserDTO dto) {

        User user = findById(id);

        Set<Hability> habilities = resolveHabilities(dto.habilities());

        Address addr = user.getAddressEntity();
        if (dto.addressEntity() != null) {
            if (addr == null) {
                addr = new Address();
            }
            addr.setStreet(dto.addressEntity().street());
            addr.setNumber(dto.addressEntity().number());
            addr.setNeighborhood(dto.addressEntity().neighborhood());
            addr.setCity(dto.addressEntity().city());
            addr.setState(dto.addressEntity().state());
            addr.setZipCode(dto.addressEntity().zipCode());
            addr.setUser(user);
        }

        user.updateBasicData(
                dto.name(),
                dto.email(),
                dto.phone(),
                addr,
                dto.income() != null ? dto.income() : BigDecimal.ZERO,
                dto.status() != null ? Status.fromValue(dto.status()) : user.getStatus(),
                dto.observations(),
                habilities);

        if (dto.photoPath() != null) {
            user.setPhotoPath(dto.photoPath());
        }

        // Atualiza a assistência, caso uma data seja enviada e o usuário esteja ativo
        if (dto.startAssistanceDate() != null) {
            boolean hasActive = user.getAssistancePeriods().stream().anyMatch(AssistancePeriod::isActive);
            if (!hasActive && user.getStatus() != Status.INACTIVE) {
                user.startAssistance(dto.startAssistanceDate());
            } else if (hasActive) {
                // Atualiza a data do período ativo atual
                user.getAssistancePeriods().stream()
                    .filter(AssistancePeriod::isActive)
                    .findFirst()
                    .ifPresent(p -> p.setStartDate(dto.startAssistanceDate()));
            }
        }

        // Atualizar dependentes
        if (dto.dependents() != null) {
            user.getDependents().clear();
            for (var depDto : dto.dependents()) {
                Dependent dep = new Dependent();
                dep.setName(depDto.name());
                if (depDto.birthDate() != null && !depDto.birthDate().isEmpty()) {
                    dep.setBirthDate(LocalDate.parse(depDto.birthDate()));
                }
                dep.setUser(user);
                user.getDependents().add(dep);
            }
        }

        return userMapper.toDTO(userRepository.save(user));
    }

    /* AUXILIAR */

    private Address buildAddress(UserDTO dto) {
        if (dto.addressEntity() == null) return null;

        Address addr = new Address();
        addr.setStreet(dto.addressEntity().street());
        addr.setNumber(dto.addressEntity().number());
        addr.setNeighborhood(dto.addressEntity().neighborhood());
        addr.setCity(dto.addressEntity().city());
        addr.setState(dto.addressEntity().state());
        addr.setZipCode(dto.addressEntity().zipCode());
        return addr;
    }

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

    @Transactional(readOnly = true)
    public UserDTO findDTOById(Long id) {
        User user = findById(id);
        return userMapper.toDTO(user);
    }

}

