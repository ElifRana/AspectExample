package com.example.aspectexample.service;

import com.example.aspectexample.dto.UserRequest;
import com.example.aspectexample.model.UserEntity;

public interface UserService {

    UserEntity getUser(int id);

    UserEntity createUser(UserRequest userRequest);

    UserEntity updateUser(int id, UserRequest userRequest);

    void deleteUser(int id);
}
