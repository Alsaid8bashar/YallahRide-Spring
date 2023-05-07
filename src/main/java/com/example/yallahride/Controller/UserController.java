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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Long> getNumberOfUsers() {
        return new ResponseEntity<>(userService.getNumberOfUser(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/activate/{id}")
    public ResponseEntity<User> activateUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.activateUserById(id), HttpStatus.OK);
    }

    @PostMapping("/deactivate/{id}")
    public ResponseEntity<User> deactivateUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deactivateUserById(id), HttpStatus.OK);
    }

    @PostMapping("/travel_preferences/{id}")
    public ResponseEntity<User> addTravelPreference(@PathVariable Long id, @RequestBody TravelPreference travelPreference) {
        return new ResponseEntity<>(userService.addTravelPreference(id, travelPreference), HttpStatus.OK);
    }

    @GetMapping("/{id}/travel-preferences")
    public ResponseEntity<Collection<TravelPreference>> getTravelPreference(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserTravelPreferences(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete/travel-preferences")
    public ResponseEntity<User> deleteTravelPreference(@PathVariable Long id, @RequestBody TravelPreference travelPreference) {
        userService.deleteTravelPreference(id, travelPreference);
        return new ResponseEntity<>(userService.deleteTravelPreference(id, travelPreference), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/add/role")
    public ResponseEntity<User> addRole(@PathVariable Long id, @RequestBody Role role) {
        return new ResponseEntity<>(userService.addRole(id, role), HttpStatus.OK);
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<Collection<Role>> getRoles(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserRoles(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete/role")
    public ResponseEntity<User> deleteRole(@PathVariable Long id, @RequestBody Role role) {
        return new ResponseEntity<>(userService.deleteRole(id, role), HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}/add/ride")
    public ResponseEntity<User> addRide(@PathVariable Long id, @RequestBody Ride ride) {
        return new ResponseEntity<>(userService.addRide(id, ride), HttpStatus.OK);
    }


    @GetMapping("{id}/rides")
    public ResponseEntity<Collection<Ride>> getRides(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserRides(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete/ride")
    public ResponseEntity<User> deleteRide(@PathVariable Long id, @RequestBody Ride ride) {
        return new ResponseEntity<>(userService.deleteRide(id, ride), HttpStatus.NO_CONTENT);
    }

}
