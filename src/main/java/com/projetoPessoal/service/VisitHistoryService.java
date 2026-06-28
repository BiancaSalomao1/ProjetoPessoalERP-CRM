package com.projetoPessoal.service;

import com.projetoPessoal.dto.VisitHistoryDTO;
import com.projetoPessoal.mapper.UserMapper;
import com.projetoPessoal.model.User;
import com.projetoPessoal.model.VisitHistory;
import com.projetoPessoal.repository.UserRepository;
import com.projetoPessoal.repository.VisitHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitHistoryService {

    private final VisitHistoryRepository visitHistoryRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<VisitHistoryDTO> listAll() {
        return visitHistoryRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VisitHistoryDTO> listByUser(Long userId) {
        return visitHistoryRepository.findByUserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public VisitHistoryDTO registerVisit(VisitHistoryDTO dto) {
        User user = userRepository.findById(dto.user().id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        VisitHistory visit = VisitHistory.builder()
                .user(user)
                .description(dto.description())
                .performedBy(dto.performedBy())
                .visitDate(dto.visitDate() != null ? dto.visitDate() : LocalDateTime.now())
                .build();

        return toDTO(visitHistoryRepository.save(visit));
    }

    @Transactional
    public VisitHistoryDTO registerVisit(Long userId, String description, String performedBy) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        VisitHistory visit = VisitHistory.builder()
                .user(user)
                .description(description)
                .performedBy(performedBy)
                .visitDate(LocalDateTime.now())
                .build();

        return toDTO(visitHistoryRepository.save(visit));
    }

    @Transactional
    public VisitHistoryDTO updateVisit(Long id, VisitHistoryDTO dto) {
        VisitHistory visit = visitHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit not found"));

        if (dto.description() != null) visit.setDescription(dto.description());
        if (dto.performedBy() != null) visit.setPerformedBy(dto.performedBy());
        if (dto.visitDate() != null) visit.setVisitDate(dto.visitDate());

        if (dto.user() != null && dto.user().id() != null && !dto.user().id().equals(visit.getUser().getId())) {
            User user = userRepository.findById(dto.user().id())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            visit.setUser(user);
        }

        return toDTO(visitHistoryRepository.save(visit));
    }

    @Transactional
    public void deleteVisit(Long id) {
        visitHistoryRepository.deleteById(id);
    }

    private VisitHistoryDTO toDTO(VisitHistory visit) {
        return VisitHistoryDTO.builder()
                .id(visit.getId())
                .visitDate(visit.getVisitDate())
                .description(visit.getDescription())
                .performedBy(visit.getPerformedBy())
                .user(userMapper.toDTO(visit.getUser()))
                .build();
    }
}
