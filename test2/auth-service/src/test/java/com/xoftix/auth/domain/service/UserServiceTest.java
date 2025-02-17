package com.xoftix.auth.domain.service;

import com.xoftix.auth.persistence.crud.UserCrudRepository;
import com.xoftix.auth.persistence.entity.Role;
import com.xoftix.auth.persistence.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserCrudRepository userCrudRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_whenEmailNotTaken_shouldSaveSuccessfully() {
        // Dado un usuario nuevo
        User user = new User();
        user.setEmail("test@example.com");
        user.setUsername("TestUser");
        user.setPassword("12345678");
        user.setBirthDate(LocalDate.of(1990,1,1));
        user.setRole(Role.USER);

        // Mock de findByEmail
        when(userCrudRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        // Mock de passwordEncoder
        when(passwordEncoder.encode("12345678")).thenReturn("encodedPassword");
        // Mock de save
        when(userCrudRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Cuando llamamos a saveUser
        User savedUser = userService.saveUser(user);

        // Entonces
        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals(Role.USER, savedUser.getRole());
        verify(userCrudRepository, times(1)).save(any(User.class));
    }

    @Test
    void saveUser_whenEmailAlreadyExists_shouldThrowException() {
        // Dado un usuario con email duplicado
        User user = new User();
        user.setEmail("duplicate@example.com");
        user.setUsername("Duplicate");
        user.setPassword("12345678");

        // Mock para simular que ya existe un usuario con ese email
        when(userCrudRepository.findByEmail("duplicate@example.com"))
                .thenReturn(Optional.of(new User()));

        // Cuando llamamos a saveUser, esperamos una excepción
        assertThrows(IllegalArgumentException.class, () -> userService.saveUser(user));
    }
}
