package com.example.movieclub.domain.comment;

import com.example.movieclub.domain.movie.Movie;
import com.example.movieclub.domain.movie.MovieRepository;
import com.example.movieclub.domain.user.AppUser;
import com.example.movieclub.domain.user.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final AppUserRepository appUserRepository;
    private final MovieRepository movieRepository;

    public CommentDto save(CommentDto commentDto, String email, Long movieId) {
        Comment comment = CommentMapper.map(commentDto);
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        comment.setMovie(movie);
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow();
        comment.setAppUser(appUser);
        commentRepository.save(comment);
        commentDto.setAddedDate(comment.getAddedDate());
        commentDto.setAuthor(email);
        return commentDto;
    }

    public List<CommentDto> findAllComments(Long id) {
        return commentRepository.findAllByMovie_IdOrderByAddedDateAsc(id)
                .stream()
                .map(CommentMapper::map)
                .toList();
    }
}
