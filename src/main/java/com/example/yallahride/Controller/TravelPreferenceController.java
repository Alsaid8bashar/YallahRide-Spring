package com.example.yallahride.Controller;

import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Service.Interface.TravelPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("travel-preference")
public class TravelPreferenceController {

    @Autowired
    TravelPreferenceService travelPreferenceService;

    @PostMapping("/create")
    public ResponseEntity<TravelPreference> saveTravelPreference(@RequestBody TravelPreference travelPreference) {
        return new ResponseEntity<>(travelPreferenceService.saveTravelPreference(travelPreference), HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<TravelPreference> updateTravelPreference(@RequestBody TravelPreference travelPreference) {
        return new ResponseEntity<>(travelPreferenceService.updateTravelPreference(travelPreference), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TravelPreference> findTravelPreferenceById(@PathVariable Long id) {
        return new ResponseEntity<>(travelPreferenceService.findTravelPreferenceById(id), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<TravelPreference>> findAllTravelPreferences() {
        return new ResponseEntity<>(travelPreferenceService.findAllTravelPreferences(), HttpStatus.OK);
    }

    @GetMapping("/find/chattiness")
    public ResponseEntity<List<TravelPreference>> findAllChattinessTravelPreferences(){
        return new ResponseEntity<>(travelPreferenceService.findAllChattinessTravelPreferences(), HttpStatus.OK);
    }
    @GetMapping("/find/smoking")
    public ResponseEntity<List<TravelPreference>> findAllSmokingTravelPreferences(){
        return new ResponseEntity<>(travelPreferenceService.findAllSmokingTravelPreferences(), HttpStatus.OK);
    }
    @GetMapping("/find/music")
    public ResponseEntity<List<TravelPreference>> findAllMusicTravelPreferences(){
        return new ResponseEntity<>(travelPreferenceService.findAllMusicTravelPreferences(), HttpStatus.OK);
    }
    @GetMapping("/find/pets")
    public ResponseEntity<List<TravelPreference>> findAllPetsTravelPreferences(){
        return new ResponseEntity<>(travelPreferenceService.findAllPetsTravelPreferences(), HttpStatus.OK);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus> deleteAllTravelPreferences() {
        travelPreferenceService.deleteAllTravelPreferences();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTravelPreferenceById(@PathVariable Long id) {
        travelPreferenceService.deleteTravelPreferenceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Long> getTravelPreferencesCount() {
        return new ResponseEntity<>(travelPreferenceService.getNumberOfTravelPreference(), HttpStatus.OK);
    }
}
