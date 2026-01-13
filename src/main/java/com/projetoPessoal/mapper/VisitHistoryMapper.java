package com.projetoPessoal.mapper;

import com.projetoPessoal.dto.VisitHistoryDTO;
import com.projetoPessoal.model.User;
import com.projetoPessoal.model.VisitHistory;
import org.springframework.stereotype.Component;

@Component
public class VisitHistoryMapper {

    /* ENTITY → DTO */

    public VisitHistoryDTO toDTO(VisitHistory history) {
        if (history == null) {
            return null;
        }

        return new VisitHistoryDTO(
                history.getId(),
                history.getVisitDate(),
                history.getDescription(),
                history.getPerformedBy(),
                history.getUser().getId()
        );
    }

    /*  CREATE  */

    public VisitHistory toEntity(User user, String description, String performedBy) {
        return VisitHistory.create(user, description, performedBy);
    }
}
