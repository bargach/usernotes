package ru.test.usernotes.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.test.usernotes.SpringMVCTest;
import ru.test.usernotes.services.UserService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class UserControllerTest extends SpringMVCTest {
    @Autowired
    UserService userService;

    @Test
    void registrationProcess() throws Exception {
        mockMvc.perform(post("/registration")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .content("username=123&password=123&name=123"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
        assertNotNull(userService.findUserByUsername("123"));
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("login"))
                .andDo(print());
    }
}