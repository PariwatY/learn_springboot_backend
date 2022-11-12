package com.example.learning.repository;

import java.util.Optional;

import com.example.learning.entity.Social;
import com.example.learning.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface SocialRepository extends CrudRepository<Social, String> {

    Optional<Social> findByUser(User user);

}
