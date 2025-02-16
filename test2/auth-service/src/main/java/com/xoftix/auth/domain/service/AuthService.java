package com.xoftix.auth.domain.service;

import com.xoftix.auth.config.JwtUtil;
import com.xoftix.auth.persistence.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public String login(String email, String password) {
        // 1. Buscar usuario
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("El email no existe"));

        // 2. Verificar contraseña
        if (!userService.matchesPassword(password, user.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        // 3. Generar token JWT
        return jwtUtil.generateToken(user);
    }
}
