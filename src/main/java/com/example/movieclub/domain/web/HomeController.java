package com.example.movieclub.domain.web;

import com.example.movieclub.domain.movie.MovieDto;
import com.example.movieclub.domain.movie.MovieService;
import com.example.movieclub.domain.session.SessionRedirect;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {
    private final MovieService movieService;
    public final static int SIZE=5;
    private final SessionRedirect sessionRedirect;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("movies",movieService.findAllPromotedMovies());
        model.addAttribute("heading", "Promowane filmy");
        model.addAttribute("description", "Filmy polecane przez nasz zespół");
        return (sessionRedirect.getUrl()==null)? "movie-listing": "redirect:"+sessionRedirect.getUrl();
    }

    @GetMapping("/movies")
    public String pageMovie(@RequestParam(defaultValue = "1") int page, Model model){
        if(page<=0) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Page<MovieDto> pageDto = movieService.getPage(page, SIZE);
        List<MovieDto> movieContent = pageDto.getContent();
        if(pageDto.getTotalPages()<page){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("movies",movieContent);
        model.addAttribute("currentPage",page);
        model.addAttribute("pages",pageDto.getTotalPages());
        model.addAttribute("heading", "Promowane filmy");
        model.addAttribute("description", "Filmy polecane przez nasz zespół");
        return "movie-listing";
    }
}
