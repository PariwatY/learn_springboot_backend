package com.example.learning.service;

import java.util.Objects;
import java.util.Optional;

import com.example.learning.entity.User;
import com.example.learning.exception.BaseException;
import com.example.learning.exception.UserException;
import com.example.learning.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User update(User user) {
        return repository.save(user);
    }

    public User updateName(String id, String name) throws BaseException {

        Optional<User> opt = repository.findById(id);
        if (opt.isEmpty()) {
            throw UserException.notFound();
        }

        User user = opt.get();
        user.setName(name);
        return repository.save(user);

    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public User create(String email, String password, String name) throws BaseException {
        if (Objects.isNull(email)) {
            throw UserException.createEmailNull();
        }
        if (Objects.isNull(password)) {
            throw UserException.creatPasswordNull();
        }
        if (Objects.isNull(name)) {
            throw UserException.createNameNull();
        }
        // Verify
        if (repository.existsById(email)) {
            throw UserException.createEmailDuplicate();
        }

        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);
        return repository.save(entity);
    }

}
