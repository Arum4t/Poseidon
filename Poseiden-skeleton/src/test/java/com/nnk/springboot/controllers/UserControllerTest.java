package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void loginSuccessTest() throws Exception {
        mockMvc.perform(formLogin("/login").user("test").password("test"))
                .andExpect(redirectedUrl("/bidList/list"))
                .andExpect(status().isFound());
    }
    @Test
    void loginFailureTest() throws Exception {
        mockMvc.perform(formLogin("/login").user("teste").password("test"))
                .andExpect(redirectedUrl("/login?error"));
    }

}