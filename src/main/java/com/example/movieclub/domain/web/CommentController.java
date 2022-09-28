package com.example.movieclub.domain.web;

import com.example.movieclub.domain.comment.CommentDto;
import com.example.movieclub.domain.comment.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/dodaj-komentarz/{id}")
    public String commentForm(Model model, @PathVariable String id){
        model.addAttribute("comment",new CommentDto());
        model.addAttribute("id",id);
        return "comment-form";
    }

    @PostMapping("/dodaj-komentarz/{id}")
    public String addComment(@PathVariable Long id, CommentDto comment, Authentication authentication){
        commentService.save(comment, authentication.getName(), id);
        return "redirect:/film/"+id;
    }
}
