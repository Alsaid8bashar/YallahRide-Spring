package com.example.yallahride.Repository;

import com.example.yallahride.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
    Optional<Feedback> findByUser_Id(Long id);
}
