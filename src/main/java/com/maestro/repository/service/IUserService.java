package com.maestro.repository.service;

import com.maestro.model.User;
import com.maestro.DTO.UserDto;

import java.util.List;

public interface IUserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(String id);
    User findOne(String username);

    User findById(String id);
}