package com.example.homework3.service;

import com.example.homework3.entity.UserLiveWithRamenResponse;
import com.example.homework3.entity.UserRamenMapResponse;
import com.example.homework3.entity.UserRequest;
import com.example.homework3.entity.UserResponse;

import java.util.List;

public interface UserService {

    UserRequest find(int id);

    List<UserResponse> findAll();

    void insert(UserRequest userRequest);

    void delete(int id);

//    List<UserRamenMapResponse> findRamenLikeMapAll();

//    List<UserLiveWithRamenResponse> findliveWithRamenAll();
}
