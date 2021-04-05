package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.AutoPopulatingList;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GamesService gamesService;

    @MockBean
    private CategoriesService categoriesService;

    @MockBean
    private DiscountsService discountsService;

    @MockBean
    private RequirementTypeService requirementTypeService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void game_ModelAttribute_200() throws Exception {
        int gameId = 1;
        GamesEntity game = new GamesEntity();
        game.setGameId(gameId);
        game.setCover(new CoversEntity());

        when(gamesService.getOne(gameId)).thenReturn(game);

        mockMvc.perform(get("/game/{id}", gameId))
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attributeExists("game"))
                .andExpect(model().attributeExists("currentDate"))
                .andExpect(model().attributeExists("discount"))
                .andExpect(model().attributeExists("review"));
    }

    @Test
    void addDiscount_ValidInput_redirectView() throws Exception {
        int gameId = 1;
        DiscountsEntity discount = new DiscountsEntity();
        discount.setDiscountId(1);
        discount.setValue(10);
        discount.setStartDate(new Date(System.currentTimeMillis()));
        discount.setEndDate(new Date(System.currentTimeMillis()));

        mockMvc.perform(post("/game/{gId}/discount/add", gameId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(discount))
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/game/" + gameId));
    }

    @Test
    void deleteGame_validUrl_redirectView() throws Exception {
        mockMvc.perform(get("/game/{id}/delete", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void editGame_GameIdEqual0_200() throws Exception {
        int gameId = 0;

        mockMvc.perform(get("/game/form")
                .param("id", String.valueOf(gameId)))
                .andExpect(status().isOk())
                .andExpect(view().name("gameForm"))
                .andExpect(model().attributeExists("game"));

        verify(gamesService, never()).getOne(gameId);
    }

    @Test
    void editGame_GameIdEqual1_GetOneUsedOnce() throws Exception {
        int gameId = 1;

        when(gamesService.getOne(gameId)).thenReturn(new GamesEntity());

        mockMvc.perform(get("/game/form")
                .param("id", String.valueOf(gameId)))
                .andExpect(status().isOk())
                .andExpect(view().name("gameForm"))
                .andExpect(model().attributeExists("game"));

        verify(gamesService, times(1)).getOne(gameId);
    }

    @Test
    void addCategory_ModelAttribute_200() throws Exception {
        GamesEntity game = new GamesEntity();
        game.setCategories(new ArrayList<>());

        mockMvc.perform(get("/game/form")
                .param("addCategory", "0")
                .sessionAttr("game", game))
                .andExpect(status().isOk())
                .andExpect(view().name("gameForm"))
                .andExpect(model().attributeExists("game"));
    }

    @Test
    void removeCategory_ModelAttribute_200() throws Exception {
        GamesEntity game = new GamesEntity();
        game.setCategories(new AutoPopulatingList<>(CategoriesEntity.class));
        game.getCategories().add(new CategoriesEntity());

        mockMvc.perform(get("/game/form")
                .param("removeCategory", "0")
                .sessionAttr("game", game))
                .andExpect(status().isOk())
                .andExpect(view().name("gameForm"))
                .andExpect(model().attributeExists("game"));
    }

    @Test
    void addPhoto_ModelAttribute_200() throws Exception {
        GamesEntity game = new GamesEntity();
        game.setPhotos(new ArrayList<>());

        mockMvc.perform(get("/game/form")
                .param("addPhoto", "0")
                .sessionAttr("game", game))
                .andExpect(status().isOk())
                .andExpect(view().name("gameForm"))
                .andExpect(model().attributeExists("game"));
    }

    @Test
    void removePhoto_ModelAttribute_200() throws Exception {
        GamesEntity game = new GamesEntity();
        game.setPhotos(new AutoPopulatingList<>(PhotosEntity.class));
        game.getPhotos().add(new PhotosEntity());

        mockMvc.perform(get("/game/form")
                .param("removePhoto", "0")
                .sessionAttr("game", game))
                .andExpect(status().isOk())
                .andExpect(view().name("gameForm"))
                .andExpect(model().attributeExists("game"));
    }

    @Test
    void addRequirement_ModelAttribute_200() throws Exception {
        GamesEntity game = new GamesEntity();
        game.setRequirements(new ArrayList<>());

        mockMvc.perform(get("/game/form")
                .param("addRequirement", "0")
                .sessionAttr("game", game))
                .andExpect(status().isOk())
                .andExpect(view().name("gameForm"))
                .andExpect(model().attributeExists("game"));
    }

    @Test
    void removeRequirement_ModelAttribute_200() throws Exception {
        GamesEntity game = new GamesEntity();
        game.setRequirements(new AutoPopulatingList<>(RequirementsEntity.class));
        game.getRequirements().add(new RequirementsEntity());

        mockMvc.perform(get("/game/form")
                .param("removeRequirement", "0")
                .sessionAttr("game", game))
                .andExpect(status().isOk())
                .andExpect(view().name("gameForm"))
                .andExpect(model().attributeExists("game"));
    }

    @Test
    void processForm_ValidInput_redirectView() throws Exception {
        int gameId = 1;
        GamesEntity game = new GamesEntity();
        game.setGameId(gameId);
        game.setTitle("title");
        game.setDescription("description");
        game.setReleaseDate(new Date(System.currentTimeMillis()));
        game.setPrice(1);
        game.setCategories(Collections.singletonList(new CategoriesEntity()));

        mockMvc.perform(post("/game/form")
                .sessionAttr("game", game)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/game/" + gameId));
    }

    @Test
    void processForm_InvalidInput_redirectView() throws Exception {
        int gameId = 1;
        GamesEntity game = new GamesEntity();
        game.setGameId(gameId);
        game.setTitle(null);
        game.setDescription(null);
        game.setReleaseDate(null);
        game.setPrice(-1);

        mockMvc.perform(post("/game/form")
                .sessionAttr("game", game)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("gameForm"))
                .andExpect(model().attributeExists("game"))
                .andExpect(model().attributeHasFieldErrors("game", "title"))
                .andExpect(model().attributeHasFieldErrors("game", "description"))
                .andExpect(model().attributeHasFieldErrors("game", "releaseDate"))
                .andExpect(model().attributeHasFieldErrors("game", "price"))
                .andExpect(model().attributeHasFieldErrors("game", "categories"));
    }


}
