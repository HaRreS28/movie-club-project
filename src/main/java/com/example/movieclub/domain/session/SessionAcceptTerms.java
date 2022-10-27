package com.example.movieclub.domain.session;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@Getter
@Setter
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS )
public class SessionAcceptTerms {
    private boolean areAccepted;
//    TODO localstorage or cookies
}
