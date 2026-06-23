package com.projetoPessoal.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev") // Usamos o profile dev para usar H2 em memória nos testes
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void publicEndpointsShouldBeAccessibleWithoutAuth() throws Exception {
        // Swagger UI / OpenAPI docs devem ser públicos
        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk());
    }

    @Test
    public void privateEndpointsShouldRequireAuthentication() throws Exception {
        // Qualquer outro endpoint (ex: GET /users) deve retornar 401 Unauthorized sem autenticação
        mockMvc.perform(get("/users"))
                .andExpect(status().isUnauthorized());
    }
}
