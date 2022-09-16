package com.example.movieclub.domain.web;

import com.example.movieclub.domain.genre.GenreDto;
import com.example.movieclub.domain.genre.GenreService;
import com.example.movieclub.domain.movie.MovieDto;
import com.example.movieclub.domain.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Controller
@AllArgsConstructor
public class GenreController {
    private final MovieService movieService;
    private final GenreService genreService;

    @GetMapping("/gatunek/{name}")
    public String getGenre(@PathVariable String name, Model model){
        GenreDto genreDto = genreService.findByName(name)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<MovieDto> movieDtos = movieService.findAllByGenreName(genreDto.getName());
        model.addAttribute("heading", genreDto.getName());
        model.addAttribute("description", genreDto.getDescription());
        model.addAttribute("movies",movieDtos);
        return "movie-listing";
    }

    @GetMapping("/gatunki-filmowe")
    public String getAllGenres(Model model){
        List<GenreDto> genreDtos = genreService.findAll();
        model.addAttribute("genres",genreDtos);
        return "genre-listing";
    }
}

