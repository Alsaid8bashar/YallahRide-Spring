package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Enum.RideStatus;
import com.example.yallahride.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;


public interface RideRepository extends JpaRepository<Ride, Long> {
    @Query("select r from Ride r where r.driver.id = ?1")
    Collection<Ride> findDriverRide(Long id);

    Collection<Ride> findByCar_User_Id(Long id);

    @Query("SELECT r FROM Ride r WHERE r.from = :from AND r.to = :to AND FUNCTION('DATE', r.departureDate) = FUNCTION('DATE', :date)")
    Collection<Ride> searchRidesByFromAndToAndDate(String from, String to, Date date);


    @Query("select r from Ride r where r.id = ?1")
    Optional<Ride> findById(Long aLong);

    @Modifying
    @Query("DELETE FROM Ride r WHERE r.id = :id")
    void deleteById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Ride r SET r.rideStatus = :status WHERE r.id = :rideId")
    int changeBookingStatusByRideId(@Param("rideId") Long rideId, @Param("status") RideStatus status);

}
