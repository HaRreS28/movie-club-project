package com.example.movieclub.domain.web.admin;

import com.example.movieclub.domain.feedback.FeedbackMessageDto;
import com.example.movieclub.domain.feedback.FeedbackMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class FeedbackManagementController {

    private final FeedbackMessageService feedbackMessageService;

    @GetMapping("/admin/sprawdz-feedback")
    public String checkFeedbacks(Model model) {
        List<FeedbackMessageDto> feedbacks = feedbackMessageService.findAllFeedbackMessagesNotChecked();
        model.addAttribute("feedbacks", feedbacks);
        return "admin/feedback-check";
    }

    @PostMapping("/admin/feedback-checked/{id}")
    public String setFeedbackChecked(@PathVariable Long id) {
        feedbackMessageService.setIsChecked(id);
        return "redirect:/admin/sprawdz-feedback";
    }
}

