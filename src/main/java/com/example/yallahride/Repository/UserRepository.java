package com.example.yallahride.Repository;

import com.example.yallahride.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    @Modifying
    @Query("UPDATE User u SET u.isVerified = true WHERE u.id = :id")
    void verifiedAccountById(@Param("id") long id);
}