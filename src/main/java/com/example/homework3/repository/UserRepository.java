package com.example.homework3.repository;

import com.example.homework3.entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Repository<User, Integer> {

    @Query(value = "SELECT * FROM user_table")
    List<User> getAll();

    @Modifying
    @Query(value = "INSERT INTO user_table(name, age) VALUES(:name, :age)")
    void insert(
            @Param("name") String name,
            @Param("age") int age
    );
}
