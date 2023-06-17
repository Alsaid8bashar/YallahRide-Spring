package com.example.yallahride.Controller;

import com.example.yallahride.Entity.Feedback;
import com.example.yallahride.Entity.Page;
import com.example.yallahride.Service.Interface.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/save")
    public ResponseEntity<Feedback> saveFeedback(@RequestBody Feedback feedback) {
        return new ResponseEntity<Feedback>(feedbackService.saveFeedback(feedback), CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Feedback> findFeedbackById(@PathVariable Long id) {
        return new ResponseEntity<Feedback>(feedbackService.findFeedbackById(id), OK);
    }

    @GetMapping("/find-user/{id}")
    public ResponseEntity<Feedback> findFeedbackByUserId(@PathVariable Long id) {
        return new ResponseEntity<Feedback>(feedbackService.findFeedbackByUserId(id), OK);
    }


}
