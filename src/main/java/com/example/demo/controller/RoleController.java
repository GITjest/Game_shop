package com.example.demo.controller;

import com.example.demo.model.UsersEntity;
import com.example.demo.service.RolesService;
import com.example.demo.service.UsersService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class RoleController {

    private final UsersService usersService;

    private final RolesService rolesService;

    public RoleController(UsersService usersService, RolesService rolesService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/role")
    public String role(Model model, Principal principal){
        model.addAttribute("users", usersService.findAllWithDifferentName(principal.getName()));
        model.addAttribute("roles", rolesService.findAll());
        return "role";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/role/set")
    public String setUserRole(@RequestParam(defaultValue = "-1", value="id") int id,
                              @RequestParam(defaultValue = "-1", value="rid") int rid) {
        if(id > 0 && rid > 0) {
            UsersEntity user = usersService.find(id);
            user.setRole(rolesService.getOne(rid));
            usersService.save(user);
        }
        return "redirect:/role";
    }
}
