package com.example.movieclub.domain.web.admin;

import com.example.movieclub.domain.genre.GenreDto;
import com.example.movieclub.domain.genre.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class GenreManagementController {

    private final GenreService genreService;

    @GetMapping("/admin/dodaj-gatunek")
    public String addGenreView(Model model){
            model.addAttribute("genre",new GenreDto());
        return "admin/genre-form";
    }

    @PostMapping("/admin/dodaj-gatunek")
    public String addGenre(GenreDto genre, RedirectAttributes redirectAttributes){
        genreService.addGenre(genre);
        redirectAttributes.addFlashAttribute(AdminController
                .NOTIFICATION_ATTRIBUTE,
                "Gatunek %s został zapisany".formatted(genre.getName()));
        return "redirect:/admin";
    }
}
