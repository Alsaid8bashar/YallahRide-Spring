package com.example.yallahride.Repository;

import com.example.yallahride.Entity.TravelPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelPreferenceRepository extends JpaRepository<TravelPreference, Long> {
    List<TravelPreference> findTravelPreferencesByCategory(String category);
}
