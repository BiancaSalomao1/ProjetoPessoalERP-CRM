package com.projetoPessoal.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DependentTest {

    @Test
    public void testCalculateAgeWithValidBirthDate() {
        Dependent dependent = new Dependent();
        dependent.setBirthDate(LocalDate.now().minusYears(10));

        assertEquals(10, dependent.getAge());
    }

    @Test
    public void testCalculateAgeWithNullBirthDate() {
        Dependent dependent = new Dependent();
        assertNull(dependent.getAge());
    }

    @Test
    public void testCalculateAgeWithFutureBirthDate() {
        Dependent dependent = new Dependent();
        dependent.setBirthDate(LocalDate.now().plusYears(1));

        assertEquals(0, dependent.getAge()); // Or handle it as an exception, but returning 0 for future dates is a safe
                                             // default for now
    }
}
