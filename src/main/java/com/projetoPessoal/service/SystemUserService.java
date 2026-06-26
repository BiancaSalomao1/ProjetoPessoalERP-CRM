package com.projetoPessoal.service;

import com.projetoPessoal.dto.SystemUserDTO;
import com.projetoPessoal.model.SystemUser;
import com.projetoPessoal.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SystemUserService {

    private final SystemUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<SystemUserDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SystemUserDTO findById(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("SystemUser not found"));
    }

    public SystemUserDTO create(SystemUserDTO dto) {
        SystemUser user = SystemUser.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .role(dto.role() != null ? dto.role() : "ADMIN")
                .createdAt(LocalDateTime.now())
                .build();
        return toDTO(repository.save(user));
    }

    public SystemUserDTO update(Long id, SystemUserDTO dto) {
        SystemUser user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SystemUser not found"));
        
        user.setUsername(dto.username());
        if (dto.password() != null && !dto.password().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.password()));
        }
        if (dto.role() != null) {
            user.setRole(dto.role());
        }
        return toDTO(repository.save(user));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SystemUserDTO toDTO(SystemUser user) {
        return SystemUserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                // Password is omitted intentionally to not return it in DTO list
                .build();
    }
}
