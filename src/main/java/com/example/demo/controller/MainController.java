package com.example.demo.controller;

import com.example.demo.model.GamesEntity;
import com.example.demo.service.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.sql.Date;

@Controller
public class MainController {

    private final GamesService gamesService;

    private final CategoriesService categoriesService;

    public MainController(GamesService gamesService, CategoriesService categoriesService) {
        this.gamesService = gamesService;
        this.categoriesService = categoriesService;
    }

    @GetMapping("/")
    public String handleRequestIndex(Model model,
                                     @RequestParam(required = false, defaultValue = "-1", value="id") int id,
                                     @RequestParam(required = false, defaultValue = "", value="title") String title,
                                     @RequestParam(required = false, defaultValue = "0", value="priceMin") double priceMin,
                                     @RequestParam(required = false, defaultValue = "99999", value="priceMax") double priceMax,
                                     @RequestParam(required = false, defaultValue = "min", value="dateMin") Date dateMin,
                                     @RequestParam(required = false, defaultValue = "max", value="dateMax") Date dateMax) {

        Collection<GamesEntity> games = id < 1
                ? gamesService.findGames(title, priceMin, priceMax, dateMin, dateMax)
                : gamesService.findGames(categoriesService.getOne(id), title, priceMin, priceMax, dateMin, dateMax);

        model.addAttribute("games", games);
        model.addAttribute("currentDate", new Timestamp(System.currentTimeMillis()));
        model.addAttribute("categories", categoriesService.findAll());
        return "index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final CustomDateEditor dateEditor = new CustomDateEditor(df, true) {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text != null) {
                    switch (text) {
                        case "min": setValue(new Date(0)); break;
                        case "max": setValue(new Date(9999999999999L)); break;
                        default: super.setAsText(text);
                    }
                }
            }
        };
        binder.registerCustomEditor(Date.class, dateEditor);
    }
}
