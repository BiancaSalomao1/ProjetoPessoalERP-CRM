package com.projetoPessoal.service;


import com.projetoPessoal.model.User;
import com.projetoPessoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//Lógica de negócio
@Service
public class UserService {

    //Implementação dos métodos
    @Autowired
    private UserRepository userRepository;

    // Buscar por ID
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado / User not found"));
    }

    // Salvar usuário
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Deletar usuário
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
