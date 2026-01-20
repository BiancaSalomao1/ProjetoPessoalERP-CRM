package com.projetoPessoal.service;

import com.projetoPessoal.exception.BusinessException;
import com.projetoPessoal.exception.UserNotFoundException;
import com.projetoPessoal.model.Status;
import com.projetoPessoal.model.User;
import com.projetoPessoal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssistancePeriodServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AssistancePeriodService service;

    @Test
    void shouldStartAssistanceForActiveUser() {

        User user = User.builder()
                .status(Status.ACTIVE)
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        service.startAssistance(1L, LocalDate.now());

        verify(userRepository).save(user);
        assertEquals(1, user.getAssistancePeriods().size());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> service.startAssistance(1L, LocalDate.now())
        );
    }

    @Test
    void shouldThrowExceptionWhenUserInactive() {

        User user = User.builder()
                .status(Status.INACTIVE)
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        assertThrows(
                BusinessException.class,
                () -> service.startAssistance(1L, LocalDate.now())
        );
    }
}
