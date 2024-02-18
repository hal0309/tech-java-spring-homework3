package com.example.homework3.service;

import com.example.homework3.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public void insert(User user);

}
