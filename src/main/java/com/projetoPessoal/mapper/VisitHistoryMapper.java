package com.projetoPessoal.mapper;

import com.projetoPessoal.dto.VisitHistoryDTO;
import com.projetoPessoal.model.User;
import com.projetoPessoal.model.VisitHistory;
import org.springframework.stereotype.Component;

@Component
public class VisitHistoryMapper {

    private final UserMapper userMapper;

    public VisitHistoryMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

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
                userMapper.toDTO(history.getUser())
        );
    }

    /*  CREATE  */

    public VisitHistory toEntity(User user, String description, String performedBy) {
        return VisitHistory.create(user, description, performedBy);
    }
}
