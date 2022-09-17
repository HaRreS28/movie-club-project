package com.example.movieclub.domain.web.admin;

import com.example.movieclub.domain.genre.GenreDto;
import com.example.movieclub.domain.genre.GenreService;
import com.example.movieclub.domain.movie.MovieSaveDto;
import com.example.movieclub.domain.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class MovieManagementController {
    private final MovieService movieService;
    private final GenreService genreService;

    @GetMapping("/admin/dodaj-film")
    public String addMovieForm(Model model){
        List<GenreDto> genreDtos = genreService.findAll();
        model.addAttribute("genres",genreDtos);
        MovieSaveDto movie = new MovieSaveDto();
        model.addAttribute("movie",movie);
        return "/admin/movie-form";
    }

    @PostMapping("/admin/dodaj-film")
    public String addMovie(MovieSaveDto movie, RedirectAttributes redirectAttributes){
        movieService.addMovie(movie);
        redirectAttributes.addFlashAttribute(AdminController.NOTIFICATION_ATTRIBUTE,
                "Film %s został zapisany".formatted(movie.getTitle()));
        return "redirect:/admin";
    }
}
