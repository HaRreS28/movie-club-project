package com.example.movieclub.domain.web.admin;

import com.example.movieclub.domain.feedback.FeedbackMessageDto;
import com.example.movieclub.domain.feedback.FeedbackMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class FeedbackManagementController {

    private final FeedbackMessageService feedbackMessageService;

    @GetMapping("/admin/sprawdz-feedback")
    public String checkFeedbacks(Model model){
        List<FeedbackMessageDto> feedbacks = feedbackMessageService.findAllFeedbackMessages();
        model.addAttribute("feedbacks",feedbacks);
        return "admin/feedback-check";
    }

}
