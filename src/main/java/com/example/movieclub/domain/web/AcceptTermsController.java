package com.example.movieclub.domain.web;


import com.example.movieclub.domain.session.SessionAcceptTerms;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
public class AcceptTermsController {
    private final SessionAcceptTerms acceptTerms;

    @PostMapping("/accept-terms")
    public String acceptTerms(HttpServletResponse response) {
//        response.addCookie(new Cookie("acceptTerms","true"));
//        acceptTerms.setAreAccepted(true);
        return "redirect:/";
    }
}
