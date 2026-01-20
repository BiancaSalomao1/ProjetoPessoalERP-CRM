package com.projetoPessoal.service;

import com.projetoPessoal.exception.UserNotFoundException;
import com.projetoPessoal.model.User;
import com.projetoPessoal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AssistancePeriodService {

    private final UserRepository userRepository;

    @Transactional
    public void startAssistance(Long userId, LocalDate startDate) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.startAssistance(startDate);

        userRepository.save(user);
    }
}
