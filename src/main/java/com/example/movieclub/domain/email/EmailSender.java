package com.example.movieclub.domain.email;

public interface EmailSender {
 void sendEmail(String to,String token);
}
