package com.example.yallahride.Repository;

import com.example.yallahride.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}