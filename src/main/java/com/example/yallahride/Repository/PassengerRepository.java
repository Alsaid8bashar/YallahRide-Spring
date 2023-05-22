package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Passenger;
import com.example.yallahride.Entity.Ride;
import com.example.yallahride.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    @Query("SELECT u FROM User u JOIN Passenger p ON u.id = p.user.id WHERE p.ride.id = :rideId")
    List<User> findRidePassenger(@Param("rideId") Long rideId);

    @Query("SELECT r FROM Ride r JOIN Passenger p ON r.id = p.ride.id WHERE p.user.id = :userId")
    List<Ride> findUserRide(@Param("userId") Long userId);

}
