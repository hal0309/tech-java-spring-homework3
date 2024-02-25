package com.example.homework3.controller;

import com.example.homework3.entity.Ramen;
import com.example.homework3.service.RamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ramen")
public class RamenController {

    @Autowired
    private RamenService ramenService;

    @GetMapping("/findById/{id}")
    public List<Ramen> findById(@PathVariable int id){
        return ramenService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable int id) {
        ramenService.deleteById(id);
        return "delete success";
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable int id, @RequestBody Ramen ramen){
        ramenService.update(id, ramen);
        return "update success";
    }

    @GetMapping("/findAll")
    public List<Ramen> findAll(){
        return ramenService.findAll();
    }

    @PostMapping("/insert")
    public String insert(@RequestBody Ramen ramen){
        ramenService.insert(ramen);
        return "insert success";
    }
}
