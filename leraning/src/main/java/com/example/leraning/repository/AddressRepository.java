package com.example.leraning.repository;

import java.util.List;
import java.util.Optional;

import com.example.leraning.entity.Address;
import com.example.leraning.entity.Social;
import com.example.leraning.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> {

    List<Address> findByUser(User user);

}
