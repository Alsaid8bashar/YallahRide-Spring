package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query(
            value = "SELECT * FROM yallah_Ride.Report  report WHERE report.user_fk = :id",
            nativeQuery = true)
    Collection<Report> findUserReport(@Param("id") Long userId);
}
