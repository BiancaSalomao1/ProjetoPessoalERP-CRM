package com.projetoPessoal.service;

import com.projetoPessoal.dto.UserDTO;
import com.projetoPessoal.exception.UserNotFoundException;
import com.projetoPessoal.mapper.UserMapper;
import com.projetoPessoal.model.Hability;
import com.projetoPessoal.model.User;
import com.projetoPessoal.repository.HabilityRepository;
import com.projetoPessoal.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final HabilityRepository habilityRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, HabilityRepository habilityRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.habilityRepository = habilityRepository;
    }

    public List<UserDTO> listAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).toList();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User saveUser(User user) {
        // Processar habilidades antes de salvar
        if (user.getHabilitySet() != null && !user.getHabilitySet().isEmpty()) {
            Set<Hability> managedHabilities = user.getHabilitySet().stream()
                    .map(this::findOrCreateHability)
                    .collect(Collectors.toSet());
            user.setHabilitySet(managedHabilities);
        }
        return userRepository.save(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = saveUser(user);
        return userMapper.toDTO(savedUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = findById(id);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setIncome(userDTO.getIncome());
        user.setNumOfDependents(userDTO.getNumOfDependents());
        user.setStatus(userDTO.getStatus());
        user.setObservations(userDTO.getObservations());

        // Adicionar suporte à foto
        if (userDTO.getPhoto() != null) {
            user.setPhoto(userDTO.getPhoto());
        }

        if (userDTO.getHabilities() != null) {
            user.setHabilitySet(mapStringsToHabilities(userDTO.getHabilities()));
        }

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
                .map(this::findOrCreateHability)
                .collect(Collectors.toSet());
    }

    private Hability findOrCreateHability(Hability hability) {
        if (hability.getName() == null || hability.getName().trim().isEmpty()) {
            return hability;
        }

        return habilityRepository.findByName(hability.getName())
                .orElseGet(() -> habilityRepository.save(hability));
    }

    private Hability findOrCreateHability(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        return habilityRepository.findByName(name)
                .orElseGet(() -> habilityRepository.save(
                        Hability.builder().name(name).build()
                ));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}