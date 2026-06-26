package com.projetoPessoal.controller;

import com.projetoPessoal.dto.VisitHistoryDTO;
import com.projetoPessoal.service.VisitHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visit-history")
@RequiredArgsConstructor
public class VisitHistoryController {

    private final VisitHistoryService service;

    @GetMapping
    public List<VisitHistoryDTO> getAll() {
        return service.listAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitHistoryDTO create(@RequestBody VisitHistoryDTO dto) {
        return service.registerVisit(dto);
    }

    @PutMapping("/{id}")
    public VisitHistoryDTO update(@PathVariable Long id, @RequestBody VisitHistoryDTO dto) {
        return service.updateVisit(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteVisit(id);
    }
}
