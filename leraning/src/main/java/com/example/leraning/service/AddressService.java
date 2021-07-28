package com.example.leraning.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.leraning.entity.Address;
import com.example.leraning.entity.Social;
import com.example.leraning.entity.User;
import com.example.leraning.exception.BaseException;
import com.example.leraning.exception.UserException;
import com.example.leraning.repository.AddressRepository;
import com.example.leraning.repository.SocialRepository;
import com.example.leraning.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;

    }

    public List<Address> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Address create(User user, String line1, String line2, String zipcode) {

        // TODO Validate
        Address entity = new Address();
        entity.setUser(user);
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setZipcode(zipcode);

        return repository.save(entity);

    }

}
