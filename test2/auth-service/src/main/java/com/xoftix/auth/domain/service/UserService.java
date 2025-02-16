package com.xoftix.auth.domain.service;

import com.xoftix.auth.persistence.crud.UserCrudRepository;
import com.xoftix.auth.persistence.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserCrudRepository userCrudRepository;

    public UserService(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
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
        return userCrudRepository.save(user);
    }

    // Eliminar un usuario por su ID
    public void deleteUserById(String id) {
        userCrudRepository.deleteById(id);
    }
}
