package com.example.homework3.repository;

import com.example.homework3.entity.Ramen;
import com.example.homework3.entity.RamenResponse;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RamenRepository extends Repository<Ramen, Integer> {

    @Query(value = "SELECT * FROM ramen_table WHERE id = :id")
    Ramen find(@Param("id") int id);

//    @Query(value = "SELECT *
    @Query(value = "SELECT ramen_table.id, ramen_table.name, price, place_table.name AS placeName" +
            "FROM ramen_table join place_table on ramen_table.placeId = place_table.id;")
    List<RamenResponse> findAll();

    @Modifying
    @Query(value = "INSERT INTO ramen_table(name, price, placeId) VALUES(:name, :price, :placeId)")
    void insert(
            @Param("name") String name,
            @Param("price") int price,
            @Param("placeId") int placeId
    );

    @Modifying
    @Query(value = "DELETE FROM ramen_table WHERE id = :id")
    void delete(@Param("id") int id);
}
