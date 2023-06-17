package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;


public interface RideRepository extends JpaRepository<Ride, Long> {
    @Query("select r from Ride r where r.driver.id = ?1")
    Collection<Ride> findDriverRide(Long id);
    Collection<Ride> findByCar_User_Id(Long id);

    @Query("SELECT r FROM Ride r WHERE r.from = :from AND r.to = :to AND FUNCTION('DATE', r.departureDate) = FUNCTION('DATE', :date)")
    Collection<Ride> searchRidesByFromAndToAndDate(String from, String to, Date date);
}
