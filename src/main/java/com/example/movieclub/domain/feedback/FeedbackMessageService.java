package com.example.movieclub.domain.feedback;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class FeedbackMessageService {
    private final FeedbackMessageRepository feedbackMessageRepository;


    @Transactional
    public FeedbackMessageDto saveFeedback(FeedbackMessageDto dto) {
        FeedbackMessage feedbackMessage = FeedbackMessageMapper.map(dto);
        feedbackMessageRepository.save(feedbackMessage);
        return dto;
    }

    public List<FeedbackMessageDto> findAllFeedbackMessages() {
        return StreamSupport.stream(feedbackMessageRepository.findAll().spliterator(), false)
                .map(FeedbackMessageMapper::map).toList();
    }

    public List<FeedbackMessageDto> findAllByTopic(String topic) {
        return feedbackMessageRepository.findAllByTopic(topic)
                .stream().map(FeedbackMessageMapper::map).toList();
    }
}
