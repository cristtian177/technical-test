package com.xoftix.auth.web.controller;

import com.xoftix.auth.domain.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Llamamos a authService.login(...) para verificar credenciales
            String result = authService.login(request.getEmail(), request.getPassword());
            // Si no lanza excepción, credenciales son válidas
            Map<String, String> response = new HashMap<>();
            response.put("message", result); // Ej. "Login correcto"
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // Si hay error de credenciales, retornamos 400
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
