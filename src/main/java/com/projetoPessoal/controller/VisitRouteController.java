package com.projetoPessoal.controller;

import com.projetoPessoal.dto.VisitRouteDTO;
import com.projetoPessoal.service.VisitRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visit-routes")
@RequiredArgsConstructor
public class VisitRouteController {

    private final VisitRouteService service;

    @GetMapping
    public List<VisitRouteDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public VisitRouteDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitRouteDTO create(@RequestBody VisitRouteDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public VisitRouteDTO update(@PathVariable Long id, @RequestBody VisitRouteDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
