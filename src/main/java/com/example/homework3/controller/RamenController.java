package com.example.homework3.controller;

import com.example.homework3.entity.Ramen;
import com.example.homework3.entity.RamenResponse;
import com.example.homework3.service.RamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ramen")
public class RamenController {

    @Autowired
    RamenService ramenService;

    @GetMapping("/find")
    public Ramen find(@RequestBody int id){
        return ramenService.find(id);
    }

    @GetMapping("/findAll")
    public List<RamenResponse> findAll(){
        return ramenService.findAll();
    }

    @PostMapping("/insert")
    public String insert(@RequestBody Ramen ramen){
        ramenService.insert(ramen);
        return "insert success";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody int id){
        ramenService.delete(id);
        return "delete success";
    }
}
