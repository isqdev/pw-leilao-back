package com.isaque.backend.controller;

import com.isaque.backend.dto.request.FeedbackRequestDTO;
import com.isaque.backend.model.Feedback;
import com.isaque.backend.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @GetMapping
    public Page<Feedback> buscarTodos(Pageable pageable) {
        return feedbackService.buscarTodos(pageable);
    }

    @PostMapping
    public Feedback inserir(@RequestBody FeedbackRequestDTO feedback) {
        return feedbackService.inserir(feedback);
    }

}
