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
        /* todo: 実装してください */
        Map<String, List<String>> ramenMap = new LinkedHashMap<>();
        List<UserResponse> userList = userService.findAll();
        List<RamenAPIResponse> ramenList = ramenService.findAll();

        /* ラーメン名をキーとした空のリストのマップを作成 */
        ramenList.stream()
                .sorted(Comparator.comparing(RamenAPIResponse::getName))  // ラーメン名でソート
                .forEach(ramen -> ramenMap.put(ramen.getName(), new ArrayList<>())); // ラーメン名をキーとした空のリストを追加

        /* 好きなラーメンのキーのリストにユーザー名(年齢)の文字列を追加 */
        userList.stream()
                .sorted((a,b) -> b.getAge() - a.getAge()) // todo: 年齢でソート
                //  ramenMap.get(user.getFavoriteRamenName()).add  ← ramenMap.put(ramen.getName(), ・・・）　
                //  ラーメンのキーのリストにユーザー名(年齢)の文字列を追加できる処理してるっぽいけど動きに違和感を感じる
                .forEach(user -> ramenMap.get(user.getFavoriteRamenName()).add(user.getName() + "("
                 + user.getAge()+ ")")); // todo: ユーザ名(年齢)に変更

        return ramenMap;
    }

    @GetMapping("/liveWithRamen")
    public List<String> liveWithRamen(){
        /* todo: 実装してください */
        List<UserResponse> userList = userService.findAll();
        List<RamenAPIResponse> ramenList = ramenService.findAll();

        return userList.stream()
                .sorted(Comparator.comparing(UserResponse::getAge)) // todo: 年齢でソート
                .filter(user -> user.getLiveInCityName().equals(
                        ramenList.stream().filter(ramen -> ramen.getName()
                                .equals(user.getFavoriteRamenName()))
                                .findFirst()
                                .get()
                                .getPlaceName() // userの好きなラーメンの所在地を取得
                ))  // 好きなラーメンの所在地とユーザの居住地が同じものを抽出
                .map(user -> user.getLiveInCityName() + "に住んでいる"
                        + user.getName() + "さんは地元の" +
                        user.getFavoriteRamenName() + "ラーメンが好きです。")  // todo: "%sに住んでいる%sさんは地元の%sラーメンが好きです。"に変更
                .toList();
    }
}
