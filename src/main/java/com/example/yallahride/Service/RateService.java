package com.example.yallahride.Service;

import com.example.yallahride.Entity.Rate;

import java.util.List;
import java.util.Optional;

public interface RateService {
    void saveRate(Rate rate);

    Optional<Rate> findRateById(Long id);

    List<Rate> findAllRates();

    void deleteAllRates();

    void deleteRateById(Long id);

    long getNumberOfRate();
}
