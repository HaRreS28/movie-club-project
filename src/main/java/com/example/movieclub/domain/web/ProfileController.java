package com.example.movieclub.domain.web;

import com.example.movieclub.domain.movie.MovieProfileDto;
import com.example.movieclub.domain.movie.MovieService;
import com.example.movieclub.domain.user.AppUserService;
import com.example.movieclub.domain.user.dto.AppUserRegistrationDto;
import com.example.movieclub.domain.verifyCode.CodeService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProfileController {
    private final AppUserService appUserService;
    private final CodeService codeService;
    private final MovieService movieService;

    @GetMapping("/twoj-profil")
    public String profile(Model model, Authentication authentication) {
        List<MovieProfileDto> moviesDto = movieService
                .getAllFilmCommentedOrRatedByUser(authentication.getName());
        model.addAttribute("movies", moviesDto);
        return "your-profile";
    }

    @GetMapping("twoj-profil/zmiana-hasla")
    public String changePassword(Model model, Authentication authentication) {
        model.addAttribute("user", appUserService.getDto(authentication.getName()));
        return "change-password-form";
    }

    @PostMapping("/zmiana-hasla")
    public String registrationForm(@Valid @ModelAttribute(name = "user")
                                   AppUserRegistrationDto user, BindingResult bindingResult,
                                   @RequestParam String confirmPassword,
                                   Authentication authentication,
                                   Model model,
                                   RedirectAttributes redirectAttributes,
                                   HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "change-password-form";
        }
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Hasła nie są takie same!");
            return "change-password-form";
        }
//        String message = appUserService.changePassword(authentication.getName(),
//                user.getPassword());
        codeService.saveCode(authentication.getName());
        httpSession.setAttribute("password", confirmPassword);
//        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/twoj-profil/confirm-password";
    }

    @GetMapping("/twoj-profil/confirm-password")
    public String confirmPassword() {
        return "confirm-pass";
    }

    @PostMapping("/twoj-profil/confirm-pass")
    public String confirmPassword(@RequestParam String code,
                                  RedirectAttributes redirectAttributes,
                                  HttpSession httpSession,
                                  Authentication authentication) {


        String password = (String) httpSession.getAttribute("password");
        String message="After signing out code is not valid";
        if(password!=null) {
             message = codeService.updateCode(code,
                    authentication.getName(),
                   password );
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/twoj-profil";
    }

}
