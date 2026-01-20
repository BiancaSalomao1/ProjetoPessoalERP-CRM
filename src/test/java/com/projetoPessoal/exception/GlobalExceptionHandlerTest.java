package com.projetoPessoal.exception;

import com.projetoPessoal.controller.UserController;
import com.projetoPessoal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@Import(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    void shouldReturn404WhenUserNotFound() throws Exception {

        when(userService.findDTOById(1L))
                .thenThrow(new UserNotFoundException(1L));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(org.hamcrest.Matchers.containsString("Usuário não encontrado")));

    }

    @Test
    @WithMockUser
    void shouldReturn400ForBusinessException() throws Exception {

        when(userService.findDTOById(1L))
                .thenThrow(new BusinessException("Usuário inativo"));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("Usuário inativo"));
    }
}
