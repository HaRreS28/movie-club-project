package com.example.movieclub.domain.web;

import com.example.movieclub.domain.user.AppUserService;
import com.example.movieclub.domain.user.dto.AppUserRegistrationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final AppUserService appUserService;

    @GetMapping("/rejestracja")
    public String registrationForm(Model model){
        model.addAttribute("user",new AppUserRegistrationDto());
        return "registration-form";
    }

    @PostMapping("/rejestracja")
    public String registrationForm(AppUserRegistrationDto user){
        appUserService.saveUser(user);
        return "redirect:login-form";
    }
}
