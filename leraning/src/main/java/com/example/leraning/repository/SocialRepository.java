package com.example.leraning.repository;

import java.util.Optional;

import com.example.leraning.entity.Social;
import com.example.leraning.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface SocialRepository extends CrudRepository<Social, String> {

    Optional<Social> findByUser(User user);

}
