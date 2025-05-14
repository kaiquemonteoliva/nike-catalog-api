package com.nike.apinike.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nike.apinike.models.UserModels;
import com.nike.apinike.repository.UserRepository;
import com.nike.apinike.request.AuthRequest;
import com.nike.apinike.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByname(login);
    }

    @Override
    public String getToken(AuthRequest auth) {
        UserModels user = userRepository.findByname(auth.getName());
//        if (user == null || !user.getPassword().equals(auth.getPassword())) {
//            throw new RuntimeException("Usuário ou senha inválidos");
//        }
        return generateToken(user);
    }


    public String generateToken(UserModels user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");
            return JWT.create()
                    .withIssuer("sitenike")
                    .withSubject(user.getName())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Fail to generate token" + exception.getMessage());
        }

    }

    @Override
    public String validateJwtToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");
            return JWT.require(algorithm)
                    .withIssuer("sitenike")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return "";

        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now()
                .plusHours(8)
                .toInstant(ZoneOffset.of("-03:00"));

    }
}
