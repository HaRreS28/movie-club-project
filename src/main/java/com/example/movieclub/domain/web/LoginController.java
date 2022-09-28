package com.example.movieclub.domain.web;

import com.example.movieclub.domain.session.SessionRedirect;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class LoginController {
    private final SessionRedirect sessionRedirect;

    @GetMapping("/login")
    public String login(@RequestHeader String referer, @RequestParam(required = false) String error){
        System.out.println(error);
        System.out.println(referer);
        redirect(referer, error);
        return "login-form";
    }

    private void redirect(String referer, String param) {
        if(param==null){
                sessionRedirect.setUrl(referer);
                System.out.println("URL "+sessionRedirect.getUrl());
        }
    }
}
