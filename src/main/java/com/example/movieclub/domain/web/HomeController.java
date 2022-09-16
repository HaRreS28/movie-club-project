package com.example.movieclub.domain.web;

import com.example.movieclub.domain.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {
    private final MovieService movieService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("movies",movieService.findAllPromotedMovies());
        model.addAttribute("heading", "Promowane filmy");
        model.addAttribute("description", "Filmy polecane przez nasz zespół");
        return "movie-listing";
    }
}
