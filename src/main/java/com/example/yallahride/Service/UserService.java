package com.example.yallahride.Service;

import com.example.yallahride.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    public Optional<User> findById(Long id);

    public List<User> findAll();

    public void deleteAll();

    public void deleteById(Long id);
}
