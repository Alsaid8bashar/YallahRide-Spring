package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Enum.RideStatus;
import com.example.yallahride.Entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByUser_IdAndIsAcceptedTrue(Long id);

    List<Passenger> findByRide_IdAndIsAcceptedTrue(Long id);

    List<Passenger> findByRide_IdAndIsAcceptedFalse(Long id);

    List<Passenger> findByRide_Id(Long id);


    @Modifying
    @Query("UPDATE Passenger p SET p.isAccepted = true WHERE p.id = :id")
    void acceptPassenger(@Param("id") long id);

    @Transactional
    @Modifying
    @Query("UPDATE Passenger p SET p.rideStatus = :status WHERE p.id = :userId and p.ride.id = :rideId")
    int cancelBookingByUserId(@Param("userId") Long userId, @Param("rideId") Long rideId, @Param("status") RideStatus status);


}
