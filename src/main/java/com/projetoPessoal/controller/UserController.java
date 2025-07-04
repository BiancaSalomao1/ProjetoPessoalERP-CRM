package com.projetoPessoal.controller;

import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.mapper.UserMapper;
import com.projetoPessoal.model.User;
import com.projetoPessoal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(userMapper.toDTO(user));  // use a instância
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> userEntities = userService.findAll(); // retorna List<User>
        List<UserDTO> users = userEntities.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
        @PostMapping
        public ResponseEntity<UserDTO> createUser (@RequestBody UserDTO userDTO){
            User user = userMapper.toEntity(userDTO);  // use a instância
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(userMapper.toDTO(savedUser));  // instância
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser (@PathVariable Long id){
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

