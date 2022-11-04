package com.example.movieclub.domain.comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentMapper {

    public static Comment map(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        comment.setAddedDate(date.format(format));
        return comment;
    }

    public static CommentDto map(Comment comment){
       return new CommentDto(comment.getAppUser().getEmail(),comment.getComment(),comment.getAddedDate());
    }
}
