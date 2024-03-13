package com.example.homework3.service;

import com.example.homework3.entity.UserRequest;
import com.example.homework3.entity.UserResponse;

import java.util.List;

public interface UserService {

    public UserRequest find(int id);

    public List<UserResponse> findAll();

    public void insert(UserRequest userRequest);

    public void delete(int id);

}
