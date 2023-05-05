package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Rate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RateService {
    Rate saveRate(Rate rate);

    Optional<Rate> findRateById(Long id);

    List<Rate> findAllRates();

    Collection<Rate> findUserRates(Long userId);

    double getUserRateByUserId(Long userId);

    void deleteAllRates();

    void deleteRateById(Long id);

    long getNumberOfRate();
}
