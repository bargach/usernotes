package ru.test.usernotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.test.usernotes.entities.User;
import ru.test.usernotes.services.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationProcess(@ModelAttribute("user") User user) {
        if (userService.findUserByUsername(user.getUsername()) != null)
            return "redirect:/registration?error=true";

        userService.addUser(user);
        return "redirect:/login?registered=true";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
