package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.Role;
import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findUserById(Long id);

    List<User> findAllUsers();

    void deleteAllUsers();

    void deleteUserById(Long id);

    long getNumberOfUser();

    User updateUser(User user);

    User activateUserById(Long userId);

    User deactivateUserById(Long userId);

    User addTravelPreference(Long userId, TravelPreference travelPreference);

    Collection<TravelPreference> getUserTravelPreferences(Long userId);

    User deleteTravelPreference(Long userId, TravelPreference travelPreference);

    User addRole(Long userId, Role role);

    Collection<Role> getUserRoles(Long userId);

    User deleteRole(Long userId, Role role);

    User addRide(Long userId, Ride ride);
    Collection<Ride> getUserRides(Long userId);

    User deleteRide(Long userId, Ride ride);


}
