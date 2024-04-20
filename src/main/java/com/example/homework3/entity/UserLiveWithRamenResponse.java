package com.example.homework3.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class UserLiveWithRamenResponse{
    @Id
    //user_table
    private final int id;
    private final String name;
    private final int age;
    private final int favorite_ramen_id;
    private final int live_in_city_id;

    // ramen_table
    private final int place_id;
    private final String favoriteRamenName;

    // place_table
    private final int place_table_id;
    private final String LiveInCityName;
}
