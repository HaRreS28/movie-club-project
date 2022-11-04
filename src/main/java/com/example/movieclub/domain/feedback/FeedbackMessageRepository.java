package com.example.movieclub.domain.feedback;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedbackMessageRepository extends CrudRepository<FeedbackMessage,Long> {

    List<FeedbackMessage> findAllByTopic(String topic);
}
