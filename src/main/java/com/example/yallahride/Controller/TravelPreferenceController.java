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
@RequestMapping("travel-preference")
public class TravelPreferenceController {

    @Autowired
    TravelPreferenceService travelPreferenceService;

    @PostMapping("/create")
    public ResponseEntity<TravelPreference>saveTravelPreference(@RequestBody TravelPreference travelPreference){
        return new ResponseEntity<>(travelPreferenceService.saveTravelPreference(travelPreference) ,HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<TravelPreference>updateTravelPreference(@PathVariable Long id){
        Optional<TravelPreference> travelPreference = travelPreferenceService.findTravelPreferenceById(id);
        if (travelPreference.isPresent()) {
            return new ResponseEntity<>(travelPreferenceService.updateTravelPreference(travelPreference.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<TravelPreference>>findTravelPreferenceById(@PathVariable Long id){
        return new ResponseEntity<>(travelPreferenceService.findTravelPreferenceById(id), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<TravelPreference>>findAllTravelPreferences(){
        return new ResponseEntity<>(travelPreferenceService.findAllTravelPreferences(), HttpStatus.OK);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus>deleteAllTravelPreferences(){
        travelPreferenceService.deleteAllTravelPreferences();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus>deleteTravelPreferenceById(@PathVariable Long id){
        travelPreferenceService.deleteTravelPreferenceById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Long>getTravelPreferencesCount(){
        return new ResponseEntity<>(travelPreferenceService.getNumberOfTravelPreference(), HttpStatus.OK);
    }
}
