package com.example.homework3.repository;

import com.example.homework3.entity.RamenAPIResponse;
import com.example.homework3.entity.RamenRequest;
import com.example.homework3.entity.RamenDBResponse;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RamenRepository extends Repository<RamenRequest, Integer> {

//    @Query(value = "SELECT * FROM ramen_table WHERE id = :id")
//    RamenRequest find(@Param("id") int id);

    @Query(value = "SELECT * FROM ramen_table WHERE id = :id")
    RamenAPIResponse find(@Param("id") int id);

//    @Query(value = "SELECT ramen_table.id, ramen_table.name, price, place_table.name AS place_name FROM ramen_table " +
//            "LEFT JOIN place_table ON ramen_table.place_id = place_table.id")
    @Query(value = "SELECT ramen_table.id, ramen_table.name, ramen_table.price," +
            " (SELECT place_table.name FROM place_table WHERE ramen_table.place_id = place_table.id) AS place_name" +
            " FROM ramen_table;")
    List<RamenDBResponse> findAll();

    @Modifying
    @Query(value = "INSERT INTO ramen_table(name, price, place_id) VALUES(:name, :price, :placeId)")
    void insert(
            @Param("name") String name,
            @Param("price") int price,
            @Param("placeId") int placeId
    );

    @Modifying
    @Query(value = "DELETE FROM ramen_table WHERE id = :id")
    void delete(@Param("id") int id);
}
