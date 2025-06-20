package com.projetoPessoal.service;

import com.projetoPessoal.exception.UserNotFoundException;
import com.projetoPessoal.model.User;
import com.projetoPessoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Buscar por ID
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // Listar todos
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Criar ou atualizar
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Atualizar usuário existente
    public User updateUser(Long id, User userDetails) {
        User user = findById(id);

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        //user.setAddress(userDetails.getAddress());
        user.setIncome(userDetails.getIncome());
        user.setNumOfDependents(userDetails.getNumOfDependents());
        user.setStatus(userDetails.getStatus());
        user.setObservations(userDetails.getObservations());

        return userRepository.save(user);
    }

    // Deletar
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
