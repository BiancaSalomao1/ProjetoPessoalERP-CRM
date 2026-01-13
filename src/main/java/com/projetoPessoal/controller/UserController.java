package com.projetoPessoal.controller;

import com.projetoPessoal.dto.UserCreateDTO;
import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.dto.UserUpdateDTO;
import com.projetoPessoal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> listAll() {
        return userService.listAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserCreateDTO dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    public UserDTO update(
            @PathVariable Long id,
            @RequestBody UserUpdateDTO dto
    ) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
