package com.projetoPessoal.service;

import com.projetoPessoal.dto.VisitHistoryDTO;
import com.projetoPessoal.mapper.VisitHistoryMapper;
import com.projetoPessoal.model.User;
import com.projetoPessoal.model.VisitHistory;
import com.projetoPessoal.repository.VisitHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitHistoryService {

    private final VisitHistoryRepository visitHistoryRepository;
    private final VisitHistoryMapper visitHistoryMapper;
    private final UserService userService;

    public VisitHistoryDTO registerVisit(
            Long userId,
            String description,
            String performedBy
    ) {
        User user = userService.findById(userId);

        VisitHistory history = visitHistoryMapper.toEntity(
                user,
                description,
                performedBy
        );

        return visitHistoryMapper.toDTO(
                visitHistoryRepository.save(history)
        );
    }

    public List<VisitHistoryDTO> listByUser(Long userId) {
        return visitHistoryRepository.findByUserId(userId)
                .stream()
                .map(visitHistoryMapper::toDTO)
                .toList();
    }
}
