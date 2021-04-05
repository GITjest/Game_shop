package com.example.demo.controller;

import com.example.demo.service.RolesService;
import com.example.demo.service.UserDetailsServiceImpl;
import com.example.demo.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private RolesService rolesService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void registrationForm_ModelAttribute_200() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registrationForm"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void registration_ValidInput_redirectView() throws Exception {
        mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userName", "user")
                .param("password", "password")
                .param("email", "user@mail.com")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

}
