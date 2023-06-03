package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query(
            value = "SELECT * FROM YallahRide.Rate rate WHERE rate.subject_fk = :id",
            nativeQuery = true)
    Collection<Rate> findUserRates(@Param("id") Long subjectId);
}
