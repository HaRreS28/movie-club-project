package com.example.movieclub.domain.feedback;

import java.time.LocalDateTime;

public class FeedbackMessageMapper {

    public static FeedbackMessage map(FeedbackMessageDto dto) {
        FeedbackMessage feedbackMessage = new FeedbackMessage();
        feedbackMessage.setMessage(dto.getMessage());
        feedbackMessage.setTopic(dto.getTopic());
        feedbackMessage.setUsername(dto.getUsername());
        feedbackMessage.setCreatedAt(LocalDateTime.now());
        return feedbackMessage;
    }

    public static FeedbackMessageDto map(FeedbackMessage feedbackMessage) {
        FeedbackMessageDto dto = new FeedbackMessageDto();
        dto.setUsername(feedbackMessage.getUsername());
        dto.setMessage(feedbackMessage.getMessage());
        dto.setTopic(feedbackMessage.getTopic());
        return dto;
    }
}
