package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Controller
@SessionAttributes(names={"game"})
public class GameController {

    private final GamesService gamesService;

    private final CategoriesService categoriesService;

    private final DiscountsService discountsService;

    private final RequirementTypeService requirementTypeService;

    public GameController(GamesService gamesService,
                          CategoriesService categoriesService,
                          DiscountsService discountsService,
                          RequirementTypeService requirementTypeService) {
        this.gamesService = gamesService;
        this.categoriesService = categoriesService;
        this.discountsService = discountsService;
        this.requirementTypeService = requirementTypeService;
    }


    @RequestMapping("/game/{id}")
    public String game(Model model, @PathVariable("id") int id) {
        GamesEntity game = gamesService.getOne(id);
        model.addAttribute("game", game);
        model.addAttribute("currentDate", new Timestamp(System.currentTimeMillis()));
        model.addAttribute("discount", new DiscountsEntity());
        model.addAttribute("review", new ReviewsEntity());
        return "game";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @PostMapping("/game/{gId}/discount/add")
    public String addDiscount(@PathVariable("gId") int id, @Valid DiscountsEntity discount, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/game/" + id;
        }
        GamesEntity game = gamesService.getOne(id);
        DiscountsEntity discountsEntity = discountsService.getOne(game);
        if(discountsEntity == null) discountsEntity = new DiscountsEntity();
        discountsEntity.setStartDate(discount.getStartDate());
        discountsEntity.setEndDate(discount.getEndDate());
        discountsEntity.setValue(discount.getValue());
        discountsEntity.setGame(game);
        discountsService.save(discountsEntity);
        return "redirect:/game/" + id;
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @RequestMapping("/game/{id}/delete")
    public String deleteGame(@PathVariable("id") int id){
        gamesService.delete(id);
        return "redirect:/";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @GetMapping("/game/form")
    public String editGame(Model model, @RequestParam(name="id", required = false, defaultValue = "-1") int id) {
        GamesEntity game;
        if(id > 0) {
            game = gamesService.getOne(id);
        } else {
            game = new GamesEntity();
            CoversEntity coversEntity = new CoversEntity();
            coversEntity.setGame(game);
            game.setCover(coversEntity);

            game.setRequirements(new AutoPopulatingList<>(RequirementsEntity.class));
            RequirementsEntity requirementsEntity = new RequirementsEntity();
            requirementsEntity.setGame(game);
            game.getRequirements().add(requirementsEntity);

            game.setPhotos(new AutoPopulatingList<>(PhotosEntity.class));
            PhotosEntity photosEntity = new PhotosEntity();
            photosEntity.setGame(game);
            game.getPhotos().add(photosEntity);

            game.setCategories(new AutoPopulatingList<>(CategoriesEntity.class));
            CategoriesEntity categoriesEntity = new CategoriesEntity();
            categoriesEntity.setGames(Collections.singletonList(game));
            game.getCategories().add(categoriesEntity);
        }
        model.addAttribute("game", game);
        return "gameForm";
    }

    @ModelAttribute("categories")
    public List<CategoriesEntity> loadCategories(){
        return categoriesService.findAll();
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @RequestMapping(value="/game/form", params={"addCategory"})
    public String addCategory(@ModelAttribute("game") GamesEntity game, BindingResult bindingResult) {
        CategoriesEntity categoriesEntity = new CategoriesEntity();
        categoriesEntity.setGames(Collections.singletonList(game));
        game.getCategories().add(categoriesEntity);
        return "gameForm";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @RequestMapping(value="/game/form", params={"removeCategory"})
    public String removeCategory(@ModelAttribute("game") GamesEntity game, BindingResult bindingResult, HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("removeCategory"));
        game.getCategories().remove(id);
        return "gameForm";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @RequestMapping(value="/game/form", params={"addPhoto"})
    public String addPhoto(@ModelAttribute("game") GamesEntity game, BindingResult bindingResult) {
        PhotosEntity photosEntity = new PhotosEntity();
        photosEntity.setGame(game);
        game.getPhotos().add(photosEntity);
        return "gameForm";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @RequestMapping(value="/game/form", params={"removePhoto"})
    public String removePhoto(@ModelAttribute("game") GamesEntity game, BindingResult bindingResult, HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("removePhoto"));
        game.getPhotos().remove(id);
        return "gameForm";
    }

    @ModelAttribute("requirementTypes")
    public List<RequirementTypesEntity> loadRequirementTypes() {
        return requirementTypeService.find();
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @RequestMapping(value="/game/form", params={"addRequirement"})
    public String addRequirement(@ModelAttribute("game") GamesEntity game, BindingResult bindingResult) {
        RequirementsEntity requirementsEntity = new RequirementsEntity();
        requirementsEntity.setGame(game);
        game.getRequirements().add(requirementsEntity);
        return "gameForm";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @RequestMapping(value="/game/form", params={"removeRequirement"})
    public String removeRequirement(@ModelAttribute("game") GamesEntity game, BindingResult bindingResult, HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("removeRequirement"));
        game.getRequirements().remove(id);
        return "gameForm";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @PostMapping("/game/form")
    public String processForm(@Valid @ModelAttribute("game") GamesEntity game, BindingResult result){
        if(result.hasErrors()){
            return "gameForm";
        }
        gamesService.save(game);
        return "redirect:/game/" + game.getGameId();
    }
}
