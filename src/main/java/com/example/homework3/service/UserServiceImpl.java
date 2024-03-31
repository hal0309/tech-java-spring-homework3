package com.example.homework3.service;

import com.example.homework3.entity.UserRequest;
import com.example.homework3.entity.UserResponse;
import com.example.homework3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserRequest find(int id) {
        return userRepository.find(id);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void insert(UserRequest userRequest) {
        userRepository.insert(userRequest.getName(), userRequest.getAge(), userRequest.getFavoriteRamenId(), userRequest.getLiveInCityId());
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }
}
