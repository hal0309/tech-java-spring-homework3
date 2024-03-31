package com.example.homework3.repository;

import com.example.homework3.entity.UserRequest;
import com.example.homework3.entity.UserResponse;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Repository<UserRequest, Integer> {

    @Query(value = "SELECT * FROM user_table WHERE id = :id")
    UserRequest find(@Param("id") int id);

//    @Query(value = "SELECT user_table.id, user_table.name, age, place_table.name AS liveInCityName FROM user_table LEFT JOIN place_table ON user_table.favorite_ramen_id = place_table.id")

    @Query(value = "SELECT user_table.id, user_table.name, user_table.age, ramen_table.name AS favorite_ramen_name, place_table.name AS live_in_city_name FROM user_table " +
            "LEFT JOIN ramen_table ON user_table.favorite_ramen_id = ramen_table.id " +
            "LEFT JOIN place_table ON user_table.liveInCityId = place_table.id ")
    List<UserResponse> findAll();

    @Modifying
    @Query(value = "INSERT INTO user_table(name, age, favorite_ramen_id) VALUES(:name, :age, :favoriteRamenId)")
    void insert(
            @Param("name") String name,
            @Param("age") int age,
            @Param("favoriteRamenId") int favoriteRamenId
    );

    @Modifying
    @Query(value = "DELETE FROM user_table WHERE id = :id")
    void delete(@Param("id") int id);
}
