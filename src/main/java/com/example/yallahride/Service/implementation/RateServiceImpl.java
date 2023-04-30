package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Rate;
import com.example.yallahride.Repository.RateRepository;
import com.example.yallahride.Service.Interface.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    RateRepository rateRepository;

    @Override
    public void saveRate(Rate rate) {
        rateRepository.save(rate);
    }

    @Override
    public Optional<Rate> findRateById(Long id) {
        return rateRepository.findById(id);
    }

    @Override
    public List<Rate> findAllRates() {
        return rateRepository.findAll();
    }

    @Override
    public void deleteAllRates() {
        rateRepository.deleteAll();
    }

    @Override
    public void deleteRateById(Long id) {
        rateRepository.deleteById(id);
    }

    @Override
    public long getNumberOfRate() {
        return rateRepository.count();
    }
}
