package com.example.movieclub.domain.web;

import com.example.movieclub.domain.session.SessionAcceptTerms;
import com.example.movieclub.domain.session.SessionRedirect;
import com.example.movieclub.domain.storage.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class LoginController {
    private final SessionRedirect sessionRedirect;
    private final SessionAcceptTerms acceptTerms;

    @GetMapping("/login")
    public String login(@RequestHeader(required = false) String referer,
                        @RequestParam(required = false) String error
            , @RequestParam(required = false) String logout,
                        @CookieValue(name = "acceptTerms",required = false) String areAccepted) {
        if (logout != null) {
            sessionRedirect.setLogged(false);
//            acceptTerms.setAreAccepted(areAccepted.equals("true"));
        }
        if (referer != null) {
            redirect(referer, error);
        }
        return "login-form";
    }

    private void redirect(String referer, String param) {
        if (param == null) {
            sessionRedirect.setUrl(referer);
        }
    }
}
