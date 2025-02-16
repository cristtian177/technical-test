package com.xoftix.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // 1️⃣ Definir el filtro de seguridad personalizado
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desactivamos CSRF para que funcione el POST desde Postman
                .csrf(AbstractHttpConfigurer::disable)
                // No queremos sesiones (usaremos JWT más adelante)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configuramos las rutas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users").permitAll()
                        .requestMatchers("/login").permitAll() // Registro sin autenticación
                        .anyRequest().authenticated()                  //  Lo demás requiere autenticación
                );
        return http.build();
    }

    // 2️⃣ Definir un PasswordEncoder (BCrypt) para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    Definir un UserDetailsService vacío
    //    para evitar que Spring cree un usuario por defecto y
    //    genere la contraseña aleatoria en los logs
    @Bean
    public UserDetailsService userDetailsService() {
        // No definimos usuarios en memoria, lo dejamos vacío
        return new InMemoryUserDetailsManager();
    }
}
