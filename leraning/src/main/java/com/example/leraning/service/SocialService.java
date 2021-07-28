package com.example.leraning.service;

import java.util.Objects;
import java.util.Optional;

import com.example.leraning.entity.Social;
import com.example.leraning.entity.User;
import com.example.leraning.exception.BaseException;
import com.example.leraning.exception.UserException;
import com.example.leraning.repository.SocialRepository;
import com.example.leraning.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialService {

    private final SocialRepository repository;

    public SocialService(SocialRepository repository) {
        this.repository = repository;

    }

    public Optional<Social> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Social create(User user, String facebook, String line, String instagram, String tiktok) {

        // TODO Validate
        Social entity = new Social();
        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTiktok(tiktok);

        return repository.save(entity);

    }

}
