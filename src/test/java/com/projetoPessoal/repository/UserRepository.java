package com.projetoPessoal.repository;

import com.projetoPessoal.model.User;

import org.springframework.data.jpa.repository.JpaRepository;


//Acesso ao banco já com os métodos de banco findById(), save() e deleteById() prontos.
public interface UserRepository extends JpaRepository<User, Long> {

    // Criar métodos personalizados:
    // Optional<User> findByEmail(String email);


}
