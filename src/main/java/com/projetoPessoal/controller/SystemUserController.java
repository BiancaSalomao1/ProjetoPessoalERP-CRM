package com.projetoPessoal.controller;

import com.projetoPessoal.dto.SystemUserDTO;
import com.projetoPessoal.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system-users")
@RequiredArgsConstructor
public class SystemUserController {

    private final SystemUserService service;

    @GetMapping
    public List<SystemUserDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SystemUserDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SystemUserDTO create(@RequestBody SystemUserDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public SystemUserDTO update(@PathVariable Long id, @RequestBody SystemUserDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
