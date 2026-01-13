package com.projetoPessoal.controller;

import com.projetoPessoal.dto.VisitHistoryDTO;
import com.projetoPessoal.service.VisitHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/visits")
@RequiredArgsConstructor
public class VisitHistoryController {

    private final VisitHistoryService visitHistoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitHistoryDTO registerVisit(
            @PathVariable Long userId,
            @RequestParam String description,
            @RequestParam String performedBy
    ) {
        return visitHistoryService.registerVisit(userId, description, performedBy);
    }

    @GetMapping
    public List<VisitHistoryDTO> listByUser(@PathVariable Long userId) {
        return visitHistoryService.listByUser(userId);
    }
}
