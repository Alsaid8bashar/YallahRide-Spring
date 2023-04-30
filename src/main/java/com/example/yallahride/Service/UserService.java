package com.example.yallahride.Service;

import com.example.yallahride.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);

    Optional<User> findUserById(Long id);

    List<User> findAllUsers();

    void deleteAllUsers();

    void deleteUserById(Long id);

    long getNumberOfUser();
}
