package com.example.demo.controller;

import com.example.demo.model.CategoriesEntity;
import com.example.demo.model.GamesEntity;
import com.example.demo.service.CategoriesService;
import com.example.demo.service.GamesService;
import com.example.demo.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GamesService gamesService;

    @MockBean
    private CategoriesService categoriesService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void handleRequestIndex_ValidInput_200() throws Exception {
        String title = "title";
        int price = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());

        when(gamesService.findGames(title, price, price, df.parse(date), df.parse(date)))
                .thenReturn(Collections.singletonList(new GamesEntity()));
        when(categoriesService.findAll()).thenReturn(Collections.singletonList(new CategoriesEntity()));

        mockMvc.perform(get("/")
                .contentType("application/json")
                .param("id", "0")
                .param("title", title)
                .param("priceMin", String.valueOf(price))
                .param("priceMax", String.valueOf(price))
                .param("dateMin", date)
                .param("dateMax", date))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("games"))
                .andExpect(model().attributeExists("currentDate"))
                .andExpect(model().attributeExists("categories"));
    }

}
