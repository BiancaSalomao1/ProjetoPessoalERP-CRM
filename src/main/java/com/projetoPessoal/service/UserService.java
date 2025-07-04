package com.projetoPessoal.service;

import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.exception.UserNotFoundException;
import com.projetoPessoal.mapper.UserMapper;
import com.projetoPessoal.model.Hability;
import com.projetoPessoal.model.User;
import com.projetoPessoal.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDTO> listAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).toList();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }



    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = findById(id);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setIncome(userDTO.getIncome());
        user.setNumOfDependents(userDTO.getNumOfDependents());
        user.setStatus(userDTO.getStatus());
        user.setObservations(userDTO.getObservations());
        user.setHabilitySet(mapStringsToHabilities(userDTO.getHabilities()));
        return userMapper.toDTO(userRepository.save(user));
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    private Set<Hability> mapStringsToHabilities(Set<String> habilities) {
        return habilities.stream()
                .map(name -> Hability.builder().name(name).build())
                .collect(Collectors.toSet());
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

}
