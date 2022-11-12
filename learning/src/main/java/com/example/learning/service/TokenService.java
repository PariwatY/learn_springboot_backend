package com.example.learning.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.learning.entity.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("@{app.token.secret}")
    private String secret;

    @Value("@{app.token.issuer}")
    private String issuer;

    public String tokennize(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withIssuer(issuer).withClaim("principal", user.getId()).withClaim("role", "user")
                .sign(algorithm);

    }
}
