package com.example.learning.mapper;

import com.example.learning.entity.User;
import com.example.learning.model.MRegisterResponse;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface userMapper {
    MRegisterResponse toRegisterResponse(User user);
}
