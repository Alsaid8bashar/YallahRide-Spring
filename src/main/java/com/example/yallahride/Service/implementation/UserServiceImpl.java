package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.User;
import com.example.yallahride.Repository.TravelPreferenceRepository;
import com.example.yallahride.Repository.UserRepository;
import com.example.yallahride.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public User activateUserById(User user) {
        user.setActive(true);
        return userRepository.save(user);
    }

    public User activateUserById(Long id) {
        User user = findUserById(id).get();
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User DeactivateUserById(User user) {
        user.setActive(false);
        return userRepository.save(user);
    }

    public User DeactivateUserById(Long id) {
        User user = findUserById(id).get();
        user.setActive(false);
        return userRepository.save(user);
    }


    @Override
    public User addTravelPreferenceToUser(User user, Long travelPreferenceID) {
        user.getTravelPreferences().add(travelPreferenceRepository.findById(travelPreferenceID).get());
        return userRepository.save(user);
    }

    public User addTravelPreferenceToUser(Long userId, Long travelPreferenceID) {
        User user = findUserById(userId).get();
        user.getTravelPreferences().add(travelPreferenceRepository.findById(travelPreferenceID).get());
        return userRepository.save(user);
    }

}
