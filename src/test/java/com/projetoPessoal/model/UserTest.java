package com.projetoPessoal.model;

import com.projetoPessoal.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldStartAssistanceForActiveUser() {

        User user = User.builder()
                .name("João")
                .status(Status.ACTIVE)
                .build();

        user.startAssistance(LocalDate.now());

        assertEquals(1, user.getAssistancePeriods().size());
    }

    @Test
    void shouldThrowExceptionWhenUserIsInactive() {

        User user = User.builder()
                .name("João")
                .status(Status.INACTIVE)
                .build();

        assertThrows(
                BusinessException.class,
                () -> user.startAssistance(LocalDate.now())
        );
    }

    @Test
    void shouldNotAllowTwoActivePeriods() {

        User user = User.builder()
                .name("João")
                .status(Status.ACTIVE)
                .build();

        user.startAssistance(LocalDate.now());

        assertThrows(
                IllegalStateException.class,
                () -> user.startAssistance(LocalDate.now())
        );
    }
}
