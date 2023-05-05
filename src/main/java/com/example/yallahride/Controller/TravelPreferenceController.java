package com.example.yallahride.Controller;

import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Service.Interface.TravelPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(name = "travel_preference")
public class TravelPreferenceController {

    @Autowired
    TravelPreferenceService travelPreferenceService;

    @PostMapping("save_travel_preference")
    public ResponseEntity<TravelPreference>saveTravelPreference(@RequestBody TravelPreference travelPreference){
        return new ResponseEntity<>(travelPreferenceService.saveTravelPreference(travelPreference) ,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<TravelPreference>updateTravelPreference(@RequestBody TravelPreference travelPreference){
        return new ResponseEntity<>(travelPreferenceService.saveTravelPreference(travelPreference) ,HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<TravelPreference>>findTravelPreferenceById(@PathVariable Long id){
        return new ResponseEntity<Optional<TravelPreference>>(travelPreferenceService.findTravelPreferenceById(id) ,HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<TravelPreference>>findAllTravelPreferences(){
        return new ResponseEntity<List<TravelPreference>>(travelPreferenceService.findAllTravelPreferences(),HttpStatus.OK);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus>deleteAllTravelPreferences(){
        travelPreferenceService.deleteAllTravelPreferences();
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus>deleteTravelPreferenceById(@PathVariable Long id){
        travelPreferenceService.deleteTravelPreferenceById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Long>getTravelPreferencesCount(){
        return new ResponseEntity<Long>(travelPreferenceService.getNumberOfTravelPreference(),HttpStatus.OK);
    }
}
