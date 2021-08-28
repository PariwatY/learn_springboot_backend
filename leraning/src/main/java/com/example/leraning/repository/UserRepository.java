package com.example.leraning.repository;

import java.util.Optional;

import com.example.leraning.entity.User;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Id> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);

    boolean existsById(String email);


    @Transactional
    void deleteById(String id);
}
