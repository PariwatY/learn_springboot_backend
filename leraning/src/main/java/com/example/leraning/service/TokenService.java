package com.example.leraning.service;

import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.leraning.entity.User;

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

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);

        Date expiresAt = calendar.getTime();

        return JWT.create().withIssuer(issuer).withClaim("principal", user.getId()).withClaim("role", "user")
                .withExpiresAt(expiresAt).sign(algorithm());

    }

    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm()).withIssuer(issuer).build(); // Reusable verifier instance
            verifier.verify(token);
            return verifier.verify(token);
        } catch (Exception e) {
            return null;
        }

    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256("secret");

    }
}
