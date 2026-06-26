package com.projetoPessoal.configuration;

import com.projetoPessoal.model.SystemUser;
import com.projetoPessoal.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SystemUserRepository systemUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (systemUserRepository.count() == 0) {
            System.out.println("Populando usuário administrador padrão (admin/admin)...");
            SystemUser admin = SystemUser.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role("ADMIN")
                    .createdAt(LocalDateTime.now())
                    .build();
            systemUserRepository.save(admin);
        }
    }
}
