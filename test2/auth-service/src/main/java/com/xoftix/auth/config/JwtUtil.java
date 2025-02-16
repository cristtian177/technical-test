package com.xoftix.auth.config;

import com.xoftix.auth.persistence.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret:MiClaveSecreta}")
    private String secretKey;

    // Duración de token en milisegundos (ej: 1 día)
    @Value("${jwt.expiration:86400000}")
    private long expirationTime;

    public String generateToken(User user) {
        long now = System.currentTimeMillis();
        Date nowDate = new Date(now);
        Date expDate = new Date(now + expirationTime);

        return Jwts.builder()
                .setSubject(user.getEmail()) // El 'subject' será el email
                .claim("role", user.getRole().name()) // Guardamos el rol
                .setIssuedAt(nowDate)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token); // lanza excepción si no es válido
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        return (String) Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }
}
