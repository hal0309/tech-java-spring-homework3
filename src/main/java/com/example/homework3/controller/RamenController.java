package com.example.homework3.controller;

import com.example.homework3.entity.Ramen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ramen")
public class RamenController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/findALl")
    public List<Map<String, Object>> findAll(){
        String sql = "SELECT * FROM ramen_table";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @PostMapping("/insert")
    public String insert(@RequestBody Ramen ramen){
        String sql = "INSERT INTO ramen_table(name, price, place) VALUES('"+ ramen.getName() +"', "+ramen.getPrice()+", '"+ramen.getPlace()+"')";
        jdbcTemplate.update(sql);
        return "insert success";
    }
}
