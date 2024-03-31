package com.example.homework3.service;

import com.example.homework3.entity.AllRamenResponse;
import com.example.homework3.entity.RamenRequest;
import com.example.homework3.entity.RamenResponse;
import com.example.homework3.entity.RamenToppingResponse;
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
    public List<AllRamenResponse> findAll() {
        // RamenResponseを取得
        List<RamenResponse> ramenResponseList = ramenRepository.findAll();

        // RamenToppingResponseを取得
        List<RamenToppingResponse> ramenToppingResponse =ramenRepository.findAllTopping();

        // AllRamenResponseを設定、レスポンスを返す
        return ramenResponseList.stream()
                .map(ramenResponse -> {
                    AllRamenResponse allRamenResponse = new AllRamenResponse();
                    allRamenResponse.setId(ramenResponse.getId());
                    allRamenResponse.setName(ramenResponse.getName());
                    allRamenResponse.setPrice(ramenResponse.getPrice());
                    allRamenResponse.setPlaceName(ramenResponse.getPlaceName());
                    allRamenResponse.setRamenToppingResponse(ramenToppingResponse);
                    return allRamenResponse;
                })
                .toList();
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
