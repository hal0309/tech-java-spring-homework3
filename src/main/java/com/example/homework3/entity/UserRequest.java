package com.example.homework3.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class UserRequest {
    @Id
    private final String name;
    private final int age;
    private final int favoriteRamenId;
}
