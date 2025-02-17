package com.xoftix.auth.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xoftix.auth.domain.service.UserService;
import com.xoftix.auth.persistence.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void saveUser_shouldReturnOk_whenUserIsValid() throws Exception {
        // Dado un usuario de ejemplo
        User user = new User();
        user.setEmail("test@example.com");
        user.setUsername("TestUser");
        user.setPassword("12345678");
        user.setBirthDate(LocalDate.of(1990,1,1));

        // Mock de userService
        when(userService.saveUser(any(User.class))).thenReturn(user);

        // Convertir a JSON
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);

        // Cuando hacemos POST /users
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                // Entonces esperamos 200 OK
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.username").value("TestUser"));
    }
}
