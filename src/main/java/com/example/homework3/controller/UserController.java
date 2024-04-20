package com.example.homework3.controller;

import com.example.homework3.entity.*;
import com.example.homework3.service.RamenService;
import com.example.homework3.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map.Entry;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RamenService ramenService;
    private String Name;
    private String age;

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
//        List<UserRamenMapResponse> userResponseList = userService.findRamenLikeMapAll();

        List<UserResponse> userResponseList = userService.findAll();
        /* todo: 実装してください */
        Map<String, List<String>> a = userResponseList.stream()
//                .sorted((a,b) -> b.getAge() - a.getAge())
                .sorted(Comparator.comparing(UserResponse::getAge).reversed())
                .collect(Collectors.groupingBy(UserResponse::getFavoriteRamenName,
                        Collectors.mapping(UserResponse::getName,Collectors
                                .toList())));

        Map<String, List<String>> b = a.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return b;
    }


    @GetMapping("/liveWithRamen")
//    public List<UserLiveWithRamenResponse> liveWithRamen(){
    public List<String> liveWithRamen(){
        /* todo: 実装してください */
//        List<UserLiveWithRamenResponse> userResponseList = userService.findliveWithRamenAll();
        List<UserResponse> userResponseList = userService.findAll();
        List<String> userResponseList2=  userResponseList.stream()
            .map(UserResponse -> UserResponse.getLiveInCityName() + "に住んでいる"
                    + UserResponse.getName() + "さんは地元の" +
                    UserResponse.getFavoriteRamenName() + "ラーメンが好きです。")
                .toList();
        return userResponseList2;
    }
}
