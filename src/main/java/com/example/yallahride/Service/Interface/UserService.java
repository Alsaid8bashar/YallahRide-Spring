package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.User;

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
    User activateUserById(User user);
    User DeactivateUserById(User user);
    User addTravelPreferenceToUser(User user, Long travelPreferenceID);
}
