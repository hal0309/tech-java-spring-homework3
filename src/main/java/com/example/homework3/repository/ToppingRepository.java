package com.example.homework3.repository;

import com.example.homework3.entity.ToppingResponse;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToppingRepository extends Repository<ToppingResponse, Integer> {
    @Query(value = "SELECT * FROM topping_table WHERE ramen_id = :ramen_id")
    List<ToppingResponse> findByRamenId(@Param("ramen_id") int ramenId);
}
