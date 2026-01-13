package com.projetoPessoal.controller;

import com.projetoPessoal.dto.ActionPlanDTO;
import com.projetoPessoal.service.ActionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/action-plans")
@RequiredArgsConstructor
public class ActionPlanController {

    private final ActionPlanService actionPlanService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActionPlanDTO create(
            @PathVariable Long userId,
            @RequestBody ActionPlanDTO dto
    ) {
        return actionPlanService.create(userId, dto);
    }

    @PatchMapping("/{actionPlanId}/fulfill")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void markAsFulfilled(@PathVariable Long actionPlanId) {
        actionPlanService.markAsFulfilled(actionPlanId);
    }

    @GetMapping
    public List<ActionPlanDTO> listByUser(@PathVariable Long userId) {
        return actionPlanService.listByUser(userId);
    }
}
