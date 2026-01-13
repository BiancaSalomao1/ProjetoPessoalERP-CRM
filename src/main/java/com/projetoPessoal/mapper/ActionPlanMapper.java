package com.projetoPessoal.mapper;

import com.projetoPessoal.dto.ActionPlanDTO;
import com.projetoPessoal.model.ActionPlan;
import com.projetoPessoal.model.User;
import org.springframework.stereotype.Component;

@Component
public class ActionPlanMapper {

    /* ENTITY → DTO */

    public ActionPlanDTO toDTO(ActionPlan actionPlan) {
        if (actionPlan == null) {
            return null;
        }

        return new ActionPlanDTO(
                actionPlan.getId(),
                actionPlan.getDescription(),
                actionPlan.getDeadline(),
                actionPlan.getFulfilled(),
                actionPlan.getUser().getId()
        );
    }

    /* CREATE  */

    public ActionPlan toEntity(User user, ActionPlanDTO dto) {
        return ActionPlan.create(
                user,
                dto.description(),
                dto.deadline()
        );
    }
}
