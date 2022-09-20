package com.example.movieclub.domain.web;

import com.example.movieclub.domain.registration.RegistrationService;
import com.example.movieclub.domain.user.dto.AppUserRegistrationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping("/rejestracja")
    public String registrationForm(Model model) {
        model.addAttribute("user", new AppUserRegistrationDto());
        return "registration-form";
    }

    @PostMapping("/rejestracja")
    public String registrationForm(@Valid @ModelAttribute(name = "user")
                                       AppUserRegistrationDto user, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) return "registration-form";

        String message = registrationService.registerWithEmail(user);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/login";
    }

    @GetMapping("/verify")
    public String confirmAccount(@RequestParam String token, Model model) {
        String confirmRegistration = registrationService.confirmRegistration(token);
        model.addAttribute("message", confirmRegistration);
        return "/login-form";
    }
}
