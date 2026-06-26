package com.projetoPessoal.service;

import com.projetoPessoal.dto.SystemUserDTO;
import com.projetoPessoal.model.SystemUser;
import com.projetoPessoal.repository.SystemUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SystemUserServiceTest {

    @Mock
    private SystemUserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SystemUserService service;

    @Test
    public void shouldFindAllSystemUsers() {
        SystemUser user = SystemUser.builder().id(1L).username("admin").role("ADMIN").build();
        when(repository.findAll()).thenReturn(List.of(user));

        List<SystemUserDTO> result = service.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("admin", result.get(0).username());
    }

    @Test
    public void shouldFindById() {
        SystemUser user = SystemUser.builder().id(1L).username("admin").role("ADMIN").build();
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        SystemUserDTO result = service.findById(1L);

        assertNotNull(result);
        assertEquals("admin", result.username());
    }

    @Test
    public void shouldCreateSystemUser() {
        SystemUserDTO dto = SystemUserDTO.builder().username("helper").password("123").role("HELPER").build();
        SystemUser saved = SystemUser.builder().id(1L).username("helper").role("HELPER").build();
        
        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
        when(repository.save(any(SystemUser.class))).thenReturn(saved);

        SystemUserDTO result = service.create(dto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("helper", result.username());
        assertEquals("HELPER", result.role());
    }

    @Test
    public void shouldUpdateSystemUser() {
        SystemUser user = SystemUser.builder().id(1L).username("admin").password("123").role("ADMIN").build();
        SystemUserDTO dto = SystemUserDTO.builder().username("admin_updated").role("SUPER_ADMIN").build();
        
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        when(repository.save(any(SystemUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SystemUserDTO result = service.update(1L, dto);

        assertNotNull(result);
        assertEquals("admin_updated", result.username());
        assertEquals("SUPER_ADMIN", result.role());
    }

    @Test
    public void shouldDeleteSystemUser() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
