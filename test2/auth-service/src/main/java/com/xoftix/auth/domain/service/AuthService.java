package com.xoftix.auth.domain.service;

import com.xoftix.auth.persistence.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public String login(String email, String password) {
        // 1. Buscar usuario por email
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("El email no existe"));

        // 2. Verificar contraseña
        //    matchesPassword(...) es un método que crearemos en UserService
        if (!userService.matchesPassword(password, user.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        // 3. Retornar un mensaje simple (sin JWT)
        return "Login correcto";
    }
}
