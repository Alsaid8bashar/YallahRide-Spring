package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    @Query("SELECT r FROM Ride r WHERE r.from = :fromLocation AND r.to = :toLocation AND r.date = :date AND r.seats >= :numSeats")
    List<Ride> searchRidesByCriteria(@Param("fromLocation") String fromLocation, @Param("toLocation") String toLocation, @Param("date") LocalDate date, @Param("numSeats") int numSeats);
}
