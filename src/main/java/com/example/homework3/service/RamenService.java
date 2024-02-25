package com.example.homework3.service;

import com.example.homework3.entity.Ramen;

import java.util.List;

public interface RamenService {

    public List<Ramen> findById(int id);

    public void deleteById(int id);

    public void update(int id, Ramen Ramen);

    public List<Ramen> findAll();

    public void insert(Ramen Ramen);

}
