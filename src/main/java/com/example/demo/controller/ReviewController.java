package com.example.demo.controller;

import com.example.demo.model.ReviewsEntity;
import com.example.demo.service.GamesService;
import com.example.demo.service.ReviewsService;
import com.example.demo.service.UsersService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@SessionAttributes(names={"review"})
public class ReviewController {

    private final ReviewsService reviewsService;

    private final UsersService usersService;

    private final GamesService gamesService;

    public ReviewController(ReviewsService reviewsService, UsersService usersService, GamesService gamesService) {
        this.reviewsService = reviewsService;
        this.usersService = usersService;
        this.gamesService = gamesService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER"})
    @RequestMapping("/game/{id}/review/{rid}/delete")
    public String deleteReview(@PathVariable("id") int id, @PathVariable("rid") int rid){
        reviewsService.deleteById(rid);
        return "redirect:/game/" + id;
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER"})
    @PostMapping("/game/{id}/review/add")
    public String addReview(@PathVariable("id") int id, @Valid ReviewsEntity review, BindingResult result, Principal principal){
        if(result.hasErrors()){
            return "redirect:/game/" + id;
        }
        review.setUser(usersService.find(principal.getName()));
        review.setGame(gamesService.getOne(id));
        reviewsService.save(review);
        return "redirect:/game/" + id;
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER"})
    @RequestMapping("/game/{id}/review/{rid}/edit")
    public String editReview(Model model, @PathVariable("rid") int rid){
        model.addAttribute("review", reviewsService.getOne(rid));
        return "review";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER"})
    @PostMapping("/game/{id}/review/edit")
    public String editReview(@PathVariable("id") int id, @Valid @ModelAttribute("review") ReviewsEntity review, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "review";
        }
        reviewsService.save(review);
        return "redirect:/game/" + id;
    }
}
