package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.PageVideo;
import com.example.yallahride.Entity.Rate;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.RateRepository;
import com.example.yallahride.Service.Interface.RateService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    final RateRepository rateRepository;

    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public Rate saveRate(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public Rate findRateById(Long id) {
        return unwrapRate(rateRepository.findById(id),id);
    }

    @Override
    public List<Rate> findAllRates() {
        return rateRepository.findAll();
    }

    @Override
    public Collection<Rate> findUserRates(Long userId) {
        return rateRepository.findUserRates(userId);
    }

    @Override
    public double getUserRateByUserId(Long userId) {
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble(df.format(rateRepository.findUserRates(userId)
                .stream()
                .mapToDouble(Rate::getRate)
                .average()
                .orElse(0)));
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

    static Rate unwrapRate(Optional<Rate> rate, Long id) {
        if (rate.isPresent()) return rate.get();
        else throw new EntityNotFoundException(id, Rate.class);
    }


}