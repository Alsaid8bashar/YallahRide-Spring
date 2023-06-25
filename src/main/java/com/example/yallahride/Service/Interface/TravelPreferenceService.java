package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.TravelPreference;

import java.util.List;
import java.util.Optional;

public interface TravelPreferenceService {
    TravelPreference saveTravelPreference(TravelPreference travelPreference);

    TravelPreference updateTravelPreference(TravelPreference travelPreference);

    TravelPreference findTravelPreferenceById(Long id);

    List<TravelPreference> findAllChattinessTravelPreferences();

    List<TravelPreference> findAllSmokingTravelPreferences();

    List<TravelPreference> findAllMusicTravelPreferences();

    List<TravelPreference> findAllPetsTravelPreferences();

    List<TravelPreference> findAllTravelPreferences();

    void deleteAllTravelPreferences();


    void deleteTravelPreferenceById(Long id);

    long getNumberOfTravelPreference();
}
