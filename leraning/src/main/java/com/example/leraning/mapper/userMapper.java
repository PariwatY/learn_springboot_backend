package com.example.leraning.mapper;

import com.example.leraning.entity.User;
import com.example.leraning.model.MRegisterResponse;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface userMapper {
    MRegisterResponse toRegisterResponse(User user);
}
