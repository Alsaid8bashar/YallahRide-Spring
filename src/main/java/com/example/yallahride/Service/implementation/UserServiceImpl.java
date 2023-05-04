package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.Role;
import com.example.yallahride.Entity.TravelPreference;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Repository.TravelPreferenceRepository;
import com.example.yallahride.Repository.UserRepository;
import com.example.yallahride.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;
    @Autowired
    TravelPreferenceRepository travelPreferenceRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public long getNumberOfUser() {
        return userRepository.count();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User activateUserById(Long userId) {
        User user = findUserById(userId).get();
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User deactivateUserById(Long userId) {
        User user = findUserById(userId).get();
        user.setActive(false);
        return userRepository.save(user);
    }

    @Override
    public User addTravelPreference(Long userId, TravelPreference travelPreference) {
        User user = findUserById(userId).get();
        user.addTravelPreference(travelPreference);
        return saveUser(user);
    }

    @Override
    public User deleteTravelPreference(Long userId, TravelPreference travelPreference) {
        User user = findUserById(userId).get();
        user.deleteTravelPreference(travelPreference);
        return saveUser(user);
    }

    @Override
    public User addRole(Long userId, Role role) {
        User user = findUserById(userId).get();
        user.addRole(role);
        return saveUser(user);
    }

    @Override
    public User deleteRole(Long userId, Role role) {
        User user = findUserById(userId).get();
        user.deleteRole(role);
        return saveUser(user);
    }

    @Override
    public User addRide(Long userId, Ride ride) {
        User user = findUserById(userId).get();
        user.addRide(ride);
        return saveUser(user);
    }

    @Override
    public User deleteRide(Long userId, Ride ride) {
        User user = findUserById(userId).get();

        user.deleteRide(ride);
        return saveUser(user);
    }


}
