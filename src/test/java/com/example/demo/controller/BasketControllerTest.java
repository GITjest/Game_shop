package com.example.demo.controller;

import com.example.demo.model.GamesEntity;
import com.example.demo.model.OrdersEntity;
import com.example.demo.model.UsersEntity;
import com.example.demo.service.GamesService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.service.UsersService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BasketController.class)
public class BasketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private OrdersService ordersService;

    @MockBean
    private GamesService gamesService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @WithMockUser(value = "admin")
    void basket_ModelAttributes_200() throws Exception {
        String userName = "admin";
        Principal principal = Mockito.mock(Principal.class);
        OrdersEntity order = new OrdersEntity();
        order.setGame(new GamesEntity());
        UsersEntity user = new UsersEntity();
        user.setUserName(userName);
        user.setOrders(Collections.singletonList(order));

        when(principal.getName()).thenReturn(userName);
        when(usersService.find(userName)).thenReturn(user);

        mockMvc.perform(get("/basket"))
                .andExpect(status().isOk())
                .andExpect(view().name("basket"))
                .andExpect(model().attributeExists("orders"))
                .andExpect(model().attributeExists("currentDate"));
    }

    @Test
    @WithMockUser(value = "admin")
    void deleteGameFromOrder_validUrl_redirectView() throws Exception {
        mockMvc.perform(get("/basket/delete")
                .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/basket"));
    }

    @Test
    @WithMockUser(value = "admin")
    void buy_validUrl_redirectView() throws Exception {
        mockMvc.perform(get("/basket/buy"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/basket"));
    }

    @Test
    @WithMockUser(value = "admin")
    void deleteGameToOrder_validUrl_redirectView() throws Exception {
        mockMvc.perform(get("/basket/add")
                .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/basket"));
    }

}
