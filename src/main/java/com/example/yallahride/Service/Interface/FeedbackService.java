package com.example.yallahride.Service.Interface;

import com.example.yallahride.Entity.Feedback;

public interface FeedbackService {
    Feedback saveFeedback(Feedback feedback);
    Feedback findFeedbackById(Long id);
    Feedback findFeedbackByUserId(Long id);
}
