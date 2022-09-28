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
    public String login(@RequestHeader String referer, @RequestParam(required = false) String error
    ,@RequestParam(required = false) String logout){
        if(logout!=null){
            sessionRedirect.setLogged(false);
        }
        redirect(referer, error);
        return "login-form";
    }

    private void redirect(String referer, String param) {
        if(param==null){
                sessionRedirect.setUrl(referer);
        }
    }
}
