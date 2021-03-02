package com.example.demo.controller;

import com.example.demo.model.OrdersEntity;
import com.example.demo.model.UsersEntity;
import com.example.demo.service.GamesService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.sql.Timestamp;

@Controller
public class BasketController {

    private final UsersService usersService;

    private final OrdersService ordersService;

    private final GamesService gamesService;

    public BasketController(UsersService usersService, OrdersService ordersService, GamesService gamesService) {
        this.usersService = usersService;
        this.ordersService = ordersService;
        this.gamesService = gamesService;
    }

    @RequestMapping("/basket")
    public String basket(Model model, Principal principal){
        UsersEntity user = usersService.find(principal.getName());
        model.addAttribute("orders", user.getOrders());
        model.addAttribute("currentDate", new Timestamp(System.currentTimeMillis()));
        return "basket";
    }

    @GetMapping(value="/basket/delete", params = {"id"})
    public String deleteGameFromOrder(int id){
        ordersService.delete(id);
        return "redirect:/basket";
    }

    @GetMapping("/basket/buy")
    public String buy(Principal principal){
        ordersService.delete(usersService.find(principal.getName()));
        return "redirect:/basket";
    }

    @GetMapping(value="/basket/add", params = {"id"})
    public String deleteGameToOrder(int id, Principal principal){
        OrdersEntity order = new OrdersEntity();
        order.setGame(gamesService.getOne(id));
        order.setUser(usersService.find(principal.getName()));
        order.setEndDate(new Timestamp(System.currentTimeMillis()));
        ordersService.save(order);
        return "redirect:/basket";
    }
}
