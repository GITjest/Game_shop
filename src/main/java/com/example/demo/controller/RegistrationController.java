package com.example.demo.controller;

import com.example.demo.model.UsersEntity;
import com.example.demo.service.RolesService;
import com.example.demo.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UsersService usersService;

    private final RolesService rolesService;

    public RegistrationController(UsersService usersService, RolesService rolesService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model){
        model.addAttribute("user", new UsersEntity());
        return "registrationForm";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("user") @Valid UsersEntity user, BindingResult bindingResult) {
        UsersEntity userExists = usersService.find(user.getUserName());
        if (userExists != null) {
            bindingResult.rejectValue("userName", "error.user");
        }

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }
        usersService.registerUser(user, rolesService.findByRole("USER"));
        return "redirect:/login";
    }
}
