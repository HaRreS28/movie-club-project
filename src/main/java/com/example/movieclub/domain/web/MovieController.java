package com.example.movieclub.domain.web;

import com.example.movieclub.domain.comment.CommentDto;
import com.example.movieclub.domain.comment.CommentService;
import com.example.movieclub.domain.movie.MovieDto;
import com.example.movieclub.domain.movie.MovieService;
import com.example.movieclub.domain.rating.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final RatingService ratingService;
    private final static int SIZE=10;
    private final CommentService commentService;

    @GetMapping("/film/{id:\\d+}")
    public String getMovie(@PathVariable Long id, Authentication authentication, Model model) {
        MovieDto movieDto = movieService.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("movie",movieDto);
        if (authentication != null) {
            String currentUserEmail = authentication.getName();
            //to wyszukujemy jego głos
            Integer rating = ratingService.getUserRatingForMovie(currentUserEmail, id).orElse(0);
            //i zapisujemy go w modelu
            model.addAttribute("userRating", rating);
        }
        List<CommentDto> allComments = commentService.findAllComments(id);
        model.addAttribute("comments",allComments);
        return "movie";
    }

    @GetMapping("/top"+SIZE)
    public String findTop10(Model model){
        List<MovieDto> topMovies=movieService.findTopMovies(SIZE);
        model.addAttribute("heading","Filmowe TOP"+SIZE);
        model.addAttribute("description","Filmy najlepiej oceniane przez użytkowników");
        model.addAttribute("movies",topMovies);
        return "movie-listing";
    }


    @GetMapping("/znajdz-film")
    public String findMovie(@RequestParam(required = false) String searched_movie,Model model){
        if(searched_movie==null){
            return "redirect:/";
        }
        String heading="Znalezione filmy";
        String description="Wszystkie filmy powiązane z podanym tytułem";
        List<MovieDto> movies = movieService.getMovies(searched_movie);
        if(movies.isEmpty()){
            heading="Nie znaleziono";
            description="Nie znaleziono żadnego filmi o tytule "+searched_movie;
        }
        model.addAttribute("heading",heading);
        model.addAttribute("description",description);
        model.addAttribute("movies",movies);
        return "movie-listing";
    }
}
