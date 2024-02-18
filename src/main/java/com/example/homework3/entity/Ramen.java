package com.example.homework3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Ramen {
    @Id
    private final int id;
    private String name;
    private int price;
    private String place;
}
