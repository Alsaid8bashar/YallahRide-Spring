package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
