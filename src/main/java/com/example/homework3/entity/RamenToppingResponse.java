package com.example.homework3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class RamenToppingResponse {
    @Id
    private final int id;
    private final String name;
    private final int price;
}
