package com.projetoPessoal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // APIs REST stateless com Basic Auth podem dispensar o CSRF se não utilizarem sessões via cookies
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos da API (Documentação Swagger)
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        // Permitir acesso ao Console do H2 (que estará desligado em prod)
                        .requestMatchers("/h2-console/**").permitAll()
                        // Qualquer outro endpoint exige autenticação
                        .anyRequest().authenticated()
                )
                // Permitir exibição de páginas em frames (exigido pelo console H2)
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
