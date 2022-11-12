package com.example.allianz.service.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.allianz.exception.token.TokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public String generateToken(String email) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("email",email).withClaim("role", "employee")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (10 * 60 * 1000)))
                .sign(algorithm);

    }


    public boolean verifyToken(String token, String email) throws TokenException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .withClaim("email",email)
                    .build();

            //Check token must not be expired and  Check data from token must be correct
            return isExpired(verifier.verify(token));
        } catch (JWTVerificationException exception){
            throw TokenException.tokenInvalid();
        }
    }


    public boolean isExpired(DecodedJWT jwt) {
        return jwt.getExpiresAt().getTime() > new Date().getTime()? true:false;
    }




}
