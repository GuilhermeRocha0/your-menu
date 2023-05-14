package br.com.fiap.yourmenu.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.yourmenu.models.Credential;
import br.com.fiap.yourmenu.models.Token;
import br.com.fiap.yourmenu.models.User;
import br.com.fiap.yourmenu.repositories.UserRepository;

@Service
public class TokenService {

    @Autowired
    UserRepository userRepository;

    @Value("${jwt.secret}")
    String secret;

    public Token generateToken(Credential credential) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
                .withSubject(credential.email())
                .withIssuer("Nubeck")
                .withExpiresAt(Instant.now().plus(20, ChronoUnit.MINUTES))
                .sign(alg);
        return new Token(token, "JWT", "Bearer");
    }

    public User getUserByToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                .withIssuer("Nubeck")
                .build()
                .verify(token)
                .getSubject();
        ;
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new JWTVerificationException("Usuario invalido"));
    }

}
