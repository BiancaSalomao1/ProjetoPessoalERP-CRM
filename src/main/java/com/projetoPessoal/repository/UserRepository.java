package com.projetoPessoal.repository;

import com.projetoPessoal.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Acesso ao banco já com os métodos de banco findById(), save() e deleteById() prontos.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
