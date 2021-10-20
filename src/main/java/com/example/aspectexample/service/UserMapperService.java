package com.example.aspectexample.service;

import com.example.aspectexample.dto.UserRequest;
import com.example.aspectexample.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapperService {

    UserEntity toDto(UserRequest userRequest);
}
