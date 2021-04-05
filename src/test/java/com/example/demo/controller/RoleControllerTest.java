package com.example.demo.controller;

import com.example.demo.model.RolesEntity;
import com.example.demo.model.UsersEntity;
import com.example.demo.service.RolesService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoleController.class)
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private RolesService rolesService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @WithMockUser("admin")
    void role_ModelAttribute_200() throws Exception {
        String userName = "user";
        Principal principal = Mockito.mock(Principal.class);

        when(principal.getName()).thenReturn(userName);
        when(usersService.findAllWithDifferentName(userName))
                .thenReturn(Collections.singletonList(new UsersEntity()));
        when(rolesService.findAll())
                .thenReturn(Collections.singletonList(new RolesEntity()));

        mockMvc.perform(get("/role"))
                .andExpect(status().isOk())
                .andExpect(view().name("role"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("roles"));
    }

    @Test
    @WithMockUser("admin")
    void setUserRole_ValidInput_SaveUsedOnce() throws Exception {
        UsersEntity user = new UsersEntity();
        int id = 1;

        when(usersService.find(id)).thenReturn(user);

        mockMvc.perform(get("/role/set")
                .param("id", String.valueOf(id))
                .param("rid", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/role"));

        verify(usersService, times(1)).save(user);
    }

    @Test
    @WithMockUser("admin")
    void setUserRole_RoleIdEqual0_SaveNeverUsed() throws Exception {
        UsersEntity user = new UsersEntity();
        int id = 1;

        when(usersService.find(id)).thenReturn(user);

        mockMvc.perform(get("/role/set")
                .param("id", String.valueOf(id))
                .param("rid", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/role"));

        verify(usersService, never()).save(user);
    }

}
