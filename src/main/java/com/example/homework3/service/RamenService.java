package com.example.homework3.service;

import com.example.homework3.entity.AllRamenResponse;
import com.example.homework3.entity.RamenRequest;
import com.example.homework3.entity.RamenResponse;

import java.util.List;

public interface RamenService {

    public RamenRequest find(int id);

    public List<AllRamenResponse> findAll();

    public void insert(RamenRequest ramenRequest);

    public void delete(int id);

}
