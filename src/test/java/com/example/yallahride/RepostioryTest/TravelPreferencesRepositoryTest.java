package com.example.yallahride.RepostioryTest;

import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Repository.TravelPreferenceRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TravelPreferencesRepositoryTest {
    @Autowired
    TravelPreferenceRepository travelPreferenceRepository;
    TravelPreference travelPreference;

    @BeforeAll
    public void setup() {
        travelPreference = travelPreferenceRepository.save(new TravelPreference("I like to travel with my dog!","21"));
    }

    @Test
    @Order(1)
    public void testCreateTravelPreference() {
        TravelPreference travelPreference = travelPreferenceRepository.save(new TravelPreference("I am talky","21"));
        Assertions.assertTrue(travelPreference.getId() > 0);
    }

    @Test
    @Order(2)
    public void testFindTravelPreferenceById() {
        TravelPreference optionalTravelPreference = travelPreferenceRepository.findById(travelPreference.getId()).get();
        Assertions.assertEquals(optionalTravelPreference.getId(), travelPreference.getId());
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testUpdateTravelPreference() {
        TravelPreference optionalTravelPreference = travelPreferenceRepository.findById(travelPreference.getId()).get();
        optionalTravelPreference.setDescription("I dont like to talk while traveling");
        TravelPreference TravelPreferenceUpdated = travelPreferenceRepository.save(optionalTravelPreference);
        Assertions.assertEquals(optionalTravelPreference.getDescription(), TravelPreferenceUpdated.getDescription());
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void testFindAllTravelPreferences() {
        List<TravelPreference> TravelPreferenceList = travelPreferenceRepository.findAll();
        Assertions.assertTrue(TravelPreferenceList.size() > 0);
    }

    @Test
    @Order(5)
    public void testDeleteTravelPreferenceByID() {
        travelPreferenceRepository.deleteById(travelPreference.getId());
        Optional<TravelPreference> optionalTravelPreference = travelPreferenceRepository.findById(travelPreference.getId());
        Assertions.assertTrue(!optionalTravelPreference.isPresent());
    }

    @Test
    @Order(6)
    public void testDeleteAllTravelPreferences() {
        travelPreferenceRepository.deleteAll();
        Assertions.assertTrue(travelPreferenceRepository.count() == 0);
    }
}
