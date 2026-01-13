package com.projetoPessoal.service;

import com.projetoPessoal.dto.ActionPlanDTO;
import com.projetoPessoal.mapper.ActionPlanMapper;
import com.projetoPessoal.model.ActionPlan;
import com.projetoPessoal.model.User;
import com.projetoPessoal.repository.ActionPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionPlanService {

    private final ActionPlanRepository actionPlanRepository;
    private final ActionPlanMapper actionPlanMapper;
    private final UserService userService;

    public ActionPlanDTO create(Long userId, ActionPlanDTO dto) {

        User user = userService.findById(userId);

        ActionPlan actionPlan = actionPlanMapper.toEntity(user, dto);

        return actionPlanMapper.toDTO(actionPlanRepository.save(actionPlan));
    }

    public void markAsFulfilled(Long actionPlanId) {
        ActionPlan plan = actionPlanRepository.findById(actionPlanId)
                .orElseThrow(() -> new RuntimeException("Plano de ação não encontrado"));

        plan.markAsFulfilled();
        actionPlanRepository.save(plan);
    }

    public List<ActionPlanDTO> listByUser(Long userId) {
        return actionPlanRepository.findByUserId(userId)
                .stream()
                .map(actionPlanMapper::toDTO)
                .toList();
    }
}
