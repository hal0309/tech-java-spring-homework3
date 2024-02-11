package com.example.homework3.controller;

import com.example.homework3.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/getUser")
    public List<Map<String, Object>> getUser(){
        String sql = "SELECT * FROM user_table";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @PostMapping("/insertUser")
    public String insertUser(@RequestBody User user){
        String sql = "INSERT INTO user_table(name, age) VALUES('"+user.name()+"', "+user.age()+")";
        jdbcTemplate.update(sql);
        return "insert success";
    }
}
