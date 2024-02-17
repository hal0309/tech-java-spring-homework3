package com.example.homework3.controller;

import com.example.homework3.model.Ramen;
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

    @GetMapping("/getRamen")
    public List<Map<String, Object>> getRamen(){
        String sql = "SELECT * FROM ramen_table";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @PostMapping("/insertRamen")
    public String insertRamen(@RequestBody Ramen ramen){
        String sql = "INSERT INTO ramen_table(name, price, place) VALUES('"+ramen.name()+"', "+ramen.price()+", '"+ramen.place()+"')";
        jdbcTemplate.update(sql);
        return "insert success";
    }
}
