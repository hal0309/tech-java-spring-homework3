package com.example.homework3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class RamenDBResponse {
    @Id
    private final int id;
    private final String name;
    private final int price;
    private final String placeName;
}
