package br.com.fiap.yourmenu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.yourmenu.models.Credential;
import br.com.fiap.yourmenu.models.User;
import br.com.fiap.yourmenu.repositories.UserRepository;
import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/api/signup")
    public ResponseEntity<User> registrar(@RequestBody @Valid User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody Credential credential) {
        manager.authenticate(credential.toAuthentication());
        return ResponseEntity.ok().build();
    }

}