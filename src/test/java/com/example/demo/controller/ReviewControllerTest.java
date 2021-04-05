package com.example.demo.controller;

import com.example.demo.model.ReviewsEntity;
import com.example.demo.service.GamesService;
import com.example.demo.service.ReviewsService;
import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReviewsService reviewsService;

    @MockBean
    private UsersService usersService;

    @MockBean
    private GamesService gamesService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void deleteReview_validUrl_redirectView() throws Exception {
        int gameId = 1;

        mockMvc.perform(get("/game/{id}/review/{rid}/delete", gameId, 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/game/" + gameId));
    }

    @Test
    void addReview_ValidInput_redirectView() throws Exception {
        int gameId = 1;
        ReviewsEntity review = new ReviewsEntity();
        review.setDescription("description");
        Principal principal = Mockito.mock(Principal.class);

        when(principal.getName()).thenReturn("userName");

        mockMvc.perform(post("/game/{id}/review/add", gameId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(review))
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/game/" + gameId));
    }

    @Test
    void addReview_InvalidInput_redirectView() throws Exception {
        int gameId = 1;
        ReviewsEntity review = new ReviewsEntity();
        review.setDescription(null);
        Principal principal = Mockito.mock(Principal.class);

        when(principal.getName()).thenReturn("userName");

        mockMvc.perform(post("/game/{id}/review/add", gameId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(review))
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/game/" + gameId));
    }

    @Test
    void editReview_ModelAttributes_200() throws Exception {
        int rid = 1;

        when(reviewsService.getOne(rid)).thenReturn(new ReviewsEntity());

        mockMvc.perform(get("/game/{id}/review/{rid}/edit", 1, rid))
                .andExpect(status().isOk())
                .andExpect(view().name("review"))
                .andExpect(model().attributeExists("review"));
    }

    @Test
    void editReview_ValidInput_redirectView() throws Exception {
        int id = 1;
        ReviewsEntity review = new ReviewsEntity();
        review.setDescription("description");

        mockMvc.perform(post("/game/{id}/review/edit", id)
                .sessionAttr("review", review)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/game/" + id));
    }

    @Test
    void editReview_InvalidInput_200() throws Exception {
        int id = 1;
        ReviewsEntity review = new ReviewsEntity();
        review.setDescription(null);

        mockMvc.perform(post("/game/{id}/review/edit", id)
                .sessionAttr("review", review)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("review"))
                .andExpect(model().attributeExists("review"))
                .andExpect(model().attributeHasFieldErrors("review", "description"));
    }


}
