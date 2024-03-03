package com.example.homework3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@Data
public class Ramen {
    @Id
    private final int id;
    private String name;
    private int price;
    private String place;

    //@Data使うと作成しなくてもよい
    public Ramen withId(int id){
        return new Ramen(id,name,price,place);
    }
}

