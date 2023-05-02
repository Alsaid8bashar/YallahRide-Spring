package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Repository.TravelPreferenceRepository;
import com.example.yallahride.Service.Interface.TravelPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelPreferenceServiceImpl implements TravelPreferenceService {

    @Autowired
    TravelPreferenceRepository travelPreferenceRepository;


    @Override
    public TravelPreference saveTravelPreference(TravelPreference travelPreference) {
        return travelPreferenceRepository.save(travelPreference);
    }

    @Override
    public TravelPreference updateTravelPreference(TravelPreference travelPreference) {
        return travelPreferenceRepository.save(travelPreference);
    }

    @Override
    public Optional<TravelPreference> findTravelPreferenceById(Long id) {
        return travelPreferenceRepository.findById(id);
    }

    @Override
    public List<TravelPreference> findAllTravelPreferences() {
        return travelPreferenceRepository.findAll();
    }

    @Override
    public void deleteAllTravelPreferences() {
        travelPreferenceRepository.deleteAll();
    }

    @Override
    public void deleteTravelPreferenceById(Long id) {
        travelPreferenceRepository.deleteById(id);
    }

    @Override
    public long getNumberOfTravelPreference() {
        return travelPreferenceRepository.count();
    }
}
