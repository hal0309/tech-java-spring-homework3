package com.example.homework3.controller;

import com.example.homework3.entity.RamenAPIResponse;
import com.example.homework3.entity.RamenDBResponse;
import com.example.homework3.entity.UserRequest;
import com.example.homework3.entity.UserResponse;
import com.example.homework3.service.RamenService;
import com.example.homework3.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RamenService ramenService;

    @GetMapping("/find")
    public UserRequest find(@RequestBody int id){
        return userService.find(id);
    }

    @GetMapping("/findAll")
    public List<UserResponse> findAll(){
        return userService.findAll();
    }

    @PostMapping("/insert")
    public String insert(@RequestBody UserRequest userRequest){
        userService.insert(userRequest);
        return "insert success";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody int id){
        userService.delete(id);
        return "delete success";
    }

    @GetMapping("/ramenLikeMap")
    public Map<String, List<String>> ramenLikeMap(){
        /* todo: 実装してください */
        return null;
    }

    @GetMapping("/liveWithRamen")
    public List<String> liveWithRamen(){
        /* todo: 実装してください */
        return null;
    }
}
