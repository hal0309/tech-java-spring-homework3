package com.example.homework3.entity;

import lombok.*;

import java.util.List;

@Data
public class AllRamenResponse {
    public int id;
    public String name;
    public int price;
    public String placeName;
    public List<RamenToppingResponse> ramenToppingResponse;
}
