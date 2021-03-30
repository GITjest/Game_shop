package com.example.demo.controller;

import com.example.demo.model.UsersEntity;
import com.example.demo.service.RolesService;
import com.example.demo.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
public class RegistrationController {

    private final UsersService usersService;

    private final RolesService rolesService;

    public RegistrationController(UsersService usersService, RolesService rolesService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("usersEntity", new UsersEntity());
        return "registrationForm";
    }

    @PostMapping(value = "/registration")
    public String createNewUser(@Valid UsersEntity user, BindingResult bindingResult) {
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
