package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CarRepository extends JpaRepository<Car,Long> {
    @Query("select c from Car c where c.user.id = ?1")
    Collection<Car> findByUser_Id(Long id);
}
