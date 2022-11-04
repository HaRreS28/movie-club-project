package com.example.movieclub.domain.web;

import com.example.movieclub.domain.feedback.FeedbackMessageDto;
import com.example.movieclub.domain.feedback.FeedbackMessageService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class FeedbackController {
private final FeedbackMessageService feedbackService;

    @GetMapping("/feedback")
    public String feedbackModel(Model model){
        model.addAttribute("feedback",new FeedbackMessageDto());
        return "feedback-form";
    }

    @PostMapping("/send-feedback")
    public String sendFeedback(@Valid FeedbackMessageDto feedbackMessageDto, BindingResult bindingResult,
                               Authentication authentication, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("error",
                    bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/feedback";
        }
        feedbackMessageDto.setUsername(authentication.getName());
        feedbackService.saveFeedback(feedbackMessageDto);
        return "redirect:/";
    }
}
