package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;

public interface RideRepository extends JpaRepository<Ride, Long> {

    Collection<Ride> searchRidesByFromAndToAndDate(String from, String to, Date date);
}
