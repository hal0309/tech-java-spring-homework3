package com.example.homework3.service;

import com.example.homework3.entity.RamenRequest;
import com.example.homework3.entity.RamenResponse;
import com.example.homework3.repository.RamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RamenServiceImpl implements RamenService {

    @Autowired
    RamenRepository ramenRepository;

    @Override
    public RamenRequest find(int id) {
        return ramenRepository.find(id);
    }

    @Override
    public List<RamenResponse> findAll() {
        return ramenRepository.findAll();
    }

    @Override
    public void insert(RamenRequest ramenRequest) {
        ramenRepository.insert(ramenRequest.getName(), ramenRequest.getPrice(), ramenRequest.getPlaceId());
    }

    @Override
    public void delete(int id) {
        ramenRepository.delete(id);
    }
}
