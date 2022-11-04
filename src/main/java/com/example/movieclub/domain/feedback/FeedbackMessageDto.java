package com.example.movieclub.domain.feedback;

import lombok.*;

import javax.validation.constraints.Size;


@AllArgsConstructor
@Getter
@ToString
@NoArgsConstructor
@Setter
public class FeedbackMessageDto {
    private String topic;
    @Size(max = 500,message = "Wiadomość nie może być dluższa niż 500 znaków")
    private String message;
    private String username;
}
