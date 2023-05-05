package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.Role;
import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(name = "user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        return new ResponseEntity<Optional<User>>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/statistics")
    public ResponseEntity<Long> getNumberOfUsers(@RequestBody User user) {
        return new ResponseEntity<Long>(userService.getNumberOfUser(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/activate")
    public ResponseEntity<HttpStatus> activateUserById(@RequestBody Long id) {
        userService.activateUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deactivate/{id}")
    public ResponseEntity<HttpStatus> deactivateUserById(@PathVariable Long id) {
        userService.deactivateUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/travel_preferences/{id}")
    public ResponseEntity<HttpStatus> addTravelPreference(@PathVariable Long id, @RequestBody TravelPreference travelPreference) {
        userService.addTravelPreference(id, travelPreference);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete_travel_preferences/{id}")
    public ResponseEntity<HttpStatus> deleteTravelPreference(@PathVariable Long id, @RequestBody TravelPreference travelPreference) {
        userService.deleteTravelPreference(id, travelPreference);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/role/{id}")
    public ResponseEntity<HttpStatus> addRole(@PathVariable Long id, @RequestBody Role role) {
        userService.addRole(id, role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete_role/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable Long id, @RequestBody Role role) {
        userService.deleteRole(id, role);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/ride/{id}")
    public ResponseEntity<HttpStatus> addRide(@PathVariable Long id, @RequestBody Ride ride) {
        userService.addRide(id, ride);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete_ride/{id}")
    public ResponseEntity<HttpStatus> deleteRide(@PathVariable Long id, @RequestBody Ride ride) {
        userService.deleteRide(id, ride);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
