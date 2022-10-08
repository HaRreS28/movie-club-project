package com.example.movieclub.domain.email;

public interface EmailSender {
 void sendEmail(String to,String token);
 void sendEmailWithCode(String to,String code);
}
