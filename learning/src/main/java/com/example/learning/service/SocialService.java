package com.example.learning.service;

import java.util.Objects;
import java.util.Optional;

import com.example.learning.entity.Social;
import com.example.learning.entity.User;
import com.example.learning.repository.SocialRepository;

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
