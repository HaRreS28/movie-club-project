package com.example.movieclub.domain.comment;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    List<Comment> findAllByMovie_IdOrderByAddedDateAsc(Long id);

    List<Comment> findAllByMovie_IdAndAppUser_Email(Long id,String email);

}
