package com.example.homework3.service;

import com.example.homework3.entity.RamenAPIResponse;
import com.example.homework3.entity.RamenRequest;
import com.example.homework3.entity.RamenDBResponse;
import com.example.homework3.repository.RamenRepository;
import com.example.homework3.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RamenServiceImpl implements RamenService {

    @Autowired
    RamenRepository ramenRepository;
    @Autowired
    ToppingRepository toppingRepository;

    @Override
    public RamenRequest find(int id) {
        return ramenRepository.find(id);
    }

    @Override
    public List<RamenAPIResponse> findAll() {
        List<RamenDBResponse> ramenList = ramenRepository.findAll();
        return ramenList.stream()
                .map(ramen ->
                        RamenAPIResponse.fromDBResponse(
                                ramen,
                                toppingRepository.findByRamenId(ramen.getId())
                        )
                ).toList();
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
