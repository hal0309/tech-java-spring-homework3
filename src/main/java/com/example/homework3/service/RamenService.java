package com.example.homework3.service;

import com.example.homework3.entity.RamenAPIResponse;
import com.example.homework3.entity.RamenRequest;
import com.example.homework3.entity.RamenDBResponse;

import java.util.List;

public interface RamenService {

    RamenAPIResponse find(int id);

    List<RamenAPIResponse> findAll();

    void insert(RamenRequest ramenRequest);

    void delete(int id);

}
