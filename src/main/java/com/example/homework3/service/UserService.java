package com.example.homework3.service;

import com.example.homework3.entity.User;

import java.util.List;

public interface UserService {

    public User find(int id);

    public List<User> findAll();

    public void insert(User user);

    public void delete(int id);

}
