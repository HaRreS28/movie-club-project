package com.example.movieclub.domain.web;

import com.example.movieclub.domain.comment.CommentDto;
import com.example.movieclub.domain.comment.CommentService;
import com.example.movieclub.domain.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final MovieService movieService;
    @GetMapping("/dodaj-komentarz/{id}")
    public String commentForm(Model model, @PathVariable Long id){
        if(id> movieService.quantityOfMovies() || id<=0) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        model.addAttribute("comment",new CommentDto());
        model.addAttribute("id",id);
        return "comment-form";
    }

    @PostMapping("/dodaj-komentarz/{id}")
    public String addComment(@PathVariable Long id, CommentDto comment, Authentication authentication){
        if(id> movieService.quantityOfMovies() || id<=0) throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        commentService.save(comment, authentication.getName(), id);
        return "redirect:/film/"+id;
    }
}
