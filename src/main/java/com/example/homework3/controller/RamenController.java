package com.example.homework3.controller;

import com.example.homework3.entity.RamenAPIResponse;
import com.example.homework3.entity.RamenRequest;
import com.example.homework3.entity.RamenDBResponse;
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
    public RamenAPIResponse find(@RequestBody int id){
        return ramenService.find(id);
    }

    @GetMapping("/findAll")
    public List<RamenAPIResponse> findAll(){
        return ramenService.findAll();
    }

    @PostMapping("/insert")
    public String insert(@RequestBody RamenRequest ramenRequest){
        ramenService.insert(ramenRequest);
        return "insert success";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody int id){
        ramenService.delete(id);
        return "delete success";
    }
}
