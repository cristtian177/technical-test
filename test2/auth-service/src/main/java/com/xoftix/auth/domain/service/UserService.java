package com.xoftix.auth.domain.service;

import com.xoftix.auth.persistence.crud.UserCrudRepository;
import com.xoftix.auth.persistence.entity.User;
import com.xoftix.auth.persistence.entity.Role;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserCrudRepository userCrudRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserCrudRepository userCrudRepository, PasswordEncoder passwordEncoder) {
        this.userCrudRepository = userCrudRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return (List<User>) userCrudRepository.findAll();
    }

    // Buscar un usuario por email
    public Optional<User> getUserByEmail(String email) {
        return userCrudRepository.findByEmail(email);
    }

    // Guardar un usuario en la base de datos
    public User saveUser(User user) {
        // Verificar si ya existe un usuario con el mismo email
        if (userCrudRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }
        // Encriptar contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Asignar rol USER por defecto
        user.setRole(Role.USER);
        return userCrudRepository.save(user);
    }

    // Eliminar un usuario por su ID
    public void deleteUserById(String id) {
        userCrudRepository.deleteById(id);
    }

    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
