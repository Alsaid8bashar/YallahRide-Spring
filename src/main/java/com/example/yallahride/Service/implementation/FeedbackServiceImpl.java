package com.example.yallahride.Service.implementation;

import com.example.yallahride.Entity.Feedback;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Exceptions.EntityNotFoundException;
import com.example.yallahride.Repository.FeedbackRepository;
import com.example.yallahride.Service.Interface.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    static Feedback unwrapFeedback(Optional<Feedback> feedback, Long id) {
        if (feedback.isPresent()) return feedback.get();
        else throw new EntityNotFoundException(id, Feedback.class);
    }

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback findFeedbackById(Long id) {
        return unwrapFeedback(feedbackRepository.findById(id), id);
    }

    @Override
    public Feedback findFeedbackByUserId(Long id) {
        return unwrapFeedback(feedbackRepository.findByUser_Id(id), id);
    }
}
