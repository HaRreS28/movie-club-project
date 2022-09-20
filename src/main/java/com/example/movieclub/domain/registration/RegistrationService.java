package com.example.movieclub.domain.registration;

import com.example.movieclub.domain.email.EmailSender;
import com.example.movieclub.domain.user.AppUserService;
import com.example.movieclub.domain.user.dto.AppUserRegistrationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailSender emailSender;
    private final AppUserService appUserService;


    @Transactional
    public String registerWithEmail(AppUserRegistrationDto registrationDto) {
        String email = registrationDto.getEmail();

        if (appUserService.userExists(email)) {
            if (!appUserService.userEnabled(email)) {
                String token = appUserService.createToken(email,appUserService.getUser(email));
                emailSender.sendEmail(email,token);
                return "User exist, confirm your email!";
            }
            else return "Account already exist!";
        }
        String token = appUserService.saveUser(registrationDto);
        emailSender.sendEmail(email,token);
        return "Successfully registered, confirm your email to login!";
    }

    public String confirmRegistration(String token){
        return appUserService.confirmToken(token);
    }
}
