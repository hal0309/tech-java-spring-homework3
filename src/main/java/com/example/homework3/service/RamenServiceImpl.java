package com.example.homework3.service;

import com.example.homework3.entity.Ramen;
import com.example.homework3.repository.RamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RamenServiceImpl implements RamenService {

    @Autowired
    RamenRepository ramenRepository;

    @Override
    public Ramen find(int id) {
        return ramenRepository.find(id);
    }

    @Override
    public List<Ramen> findAll() {
        return ramenRepository.findAll();
    }

    @Override
    public void insert(Ramen ramen) {
        ramenRepository.insert(ramen.getName(), ramen.getPrice(), ramen.getPlace());
    }

    @Override
    public void delete(int id) {
        ramenRepository.delete(id);
    }
}
