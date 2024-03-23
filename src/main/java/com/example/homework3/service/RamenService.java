package com.example.homework3.service;

import com.example.homework3.entity.Ramen;
import com.example.homework3.entity.RamenResponse;

import java.util.List;

public interface RamenService {

    public Ramen find(int id);

    public List<RamenResponse> findAll();

    public void insert(Ramen ramen);

    public void delete(int id);

}
