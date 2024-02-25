package com.example.homework3.repository;

import com.example.homework3.entity.Ramen;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RamenRepository extends Repository<Ramen, Integer> {

    @Query(value = "SELECT * FROM ramen_table WHERE id = :id")
    List<Ramen> findById(int id);

    @Modifying
    @Query(value = "DELETE FROM ramen_table WHERE id = :id")
    void deleteById(int id);

    @Modifying
    @Query(value = "UPDATE ramen_table SET name = :name, price = :price, place = :place WHERE id = :id")
    void update(
            @Param("id") int id,
            @Param("name") String name,
            @Param("price") int price,
            @Param("place") String place
    );

    @Query(value = "SELECT * FROM ramen_table")
    List<Ramen> findAll();

    @Modifying
    @Query(value = "INSERT INTO ramen_table(name, price, place) VALUES(:name, :price, :place)")
    void insert(
            @Param("name") String name,
            @Param("price") int price,
            @Param("place") String place
    );

}
