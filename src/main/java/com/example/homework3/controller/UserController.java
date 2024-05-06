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
    public UserResponse find(@RequestBody int id){
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
        Map<String, List<String>> ramenMap = new LinkedHashMap<>();
        List<UserResponse> userList = userService.findAll();
        List<RamenAPIResponse> ramenList = ramenService.findAll();

        ramenList.stream()
                .sorted(Comparator.comparing(RamenAPIResponse::getName))
                .forEach(ramen -> ramenMap.put(ramen.getName(), new ArrayList<>()));
        userList.stream()
                .sorted(Comparator.comparing(UserResponse::getAge).reversed())
                .forEach(user -> ramenMap.get(user.getFavoriteRamenName()).add(String.format("%s(%d)", user.getName(), user.getAge())));

        return ramenMap;
    }

    @GetMapping("/liveWithRamen")
    public List<String> liveWithRamen(){
        List<UserResponse> userList = userService.findAll();
        List<RamenAPIResponse> ramenList = ramenService.findAll();

        return userList.stream()
                .sorted(Comparator.comparing(UserResponse::getAge))
                .filter(user -> user.getLiveInCityName().equals(
                        ramenList.stream().filter(ramen -> ramen.getName().equals(user.getFavoriteRamenName())).findFirst().get().getPlaceName()
                ))
                .map(user -> String.format("%sに住んでいる%sさんは地元の%sラーメンが好きです。",
                        user.getLiveInCityName(), user.getName(), user.getFavoriteRamenName())
                )
                .toList();
    }

    @GetMapping("/liveInCityMap")
    public Map<String, List<String>> liveInCityMap(){
        Map<String, List<String>> liveInCityMap = new HashMap<>();
        List<UserResponse> userList = userService.findAll();

        userList.stream()
                .sorted(Comparator.comparing(UserResponse::getAge))
                .forEach(user -> {
                    if (!liveInCityMap.containsKey(user.getLiveInCityName())) {
                        liveInCityMap.put(user.getLiveInCityName(), new ArrayList<>());
                    }
                    liveInCityMap.get(user.getLiveInCityName()).add(user.getName());
                });
        return liveInCityMap;

    }

    @GetMapping("/noriLikeUser")
    public List<String> noriLikeUser(){
        List<UserResponse> userList = userService.findAll();
        List<RamenAPIResponse> ramenList = ramenService.findAll();
        return userList.stream()
                .filter(user -> ramenList.stream().filter(ramen -> ramen.getName().equals(user.getFavoriteRamenName())).findFirst().get().getToppingList().stream().anyMatch(topping -> Objects.equals(topping.getName(), "Nori")))
                .sorted(Comparator.comparing(UserResponse::getName))
                .map(user -> user.getName())
                .toList();

    }
}
