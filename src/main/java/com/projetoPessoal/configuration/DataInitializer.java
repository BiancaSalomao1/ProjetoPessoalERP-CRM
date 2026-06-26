package com.projetoPessoal.configuration;

import com.projetoPessoal.model.SystemUser;
import com.projetoPessoal.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SystemUserRepository systemUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.initial.username:admin}")
    private String adminUsername;

    @Value("${admin.initial.password:CHANGEME}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        if (systemUserRepository.count() == 0) {
            System.out.println("Populando usuário administrador padrão...");
            SystemUser admin = SystemUser.builder()
                    .username(adminUsername)
                    .password(passwordEncoder.encode(adminPassword))
                    .role("ADMIN")
                    .createdAt(LocalDateTime.now())
                    .build();
            systemUserRepository.save(admin);
        }
    }
}
