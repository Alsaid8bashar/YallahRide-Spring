package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Entity.TravelPreferenceCategory;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.TravelPreferenceRepository;
import com.example.yallahride.Service.Interface.TravelPreferenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelPreferenceServiceImpl implements TravelPreferenceService {

    final private TravelPreferenceRepository travelPreferenceRepository;

    public TravelPreferenceServiceImpl(TravelPreferenceRepository travelPreferenceRepository) {
        this.travelPreferenceRepository = travelPreferenceRepository;
    }


    @Override
    public TravelPreference saveTravelPreference(TravelPreference travelPreference) {
        return travelPreferenceRepository.save(travelPreference);
    }

    @Override
    public TravelPreference updateTravelPreference(TravelPreference travelPreference) {
        return travelPreferenceRepository.save(travelPreference);
    }

    @Override
    public TravelPreference findTravelPreferenceById(Long id) {
        return unwrapTravelPreference(travelPreferenceRepository.findById(id),id);
    }

    @Override
    public List<TravelPreference> findAllChattinessTravelPreferences() {
        return travelPreferenceRepository.findTravelPreferencesByCategory(TravelPreferenceCategory.Chattiness.toString());
    }

    @Override
    public List<TravelPreference> findAllSmokingTravelPreferences() {
        return travelPreferenceRepository.findTravelPreferencesByCategory(TravelPreferenceCategory.Smoking.toString());
    }

    @Override
    public List<TravelPreference> findAllMusicTravelPreferences() {
        return travelPreferenceRepository.findTravelPreferencesByCategory(TravelPreferenceCategory.Music.toString());
    }

    @Override
    public List<TravelPreference> findAllPetsTravelPreferences() {
        return travelPreferenceRepository.findTravelPreferencesByCategory(TravelPreferenceCategory.Pets.toString());
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

    static TravelPreference unwrapTravelPreference(Optional<TravelPreference> travelPreference, Long id) {
        if (travelPreference.isPresent()) return travelPreference.get();
        else throw new EntityNotFoundException(id, Ride.class);
    }
}
