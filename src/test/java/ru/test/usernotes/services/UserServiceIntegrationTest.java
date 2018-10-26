package ru.test.usernotes.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.test.usernotes.SpringTest;
import ru.test.usernotes.entities.User;

class UserServiceIntegrationTest extends SpringTest {
    @Autowired
    UserServiceImpl userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setName("name");
        userService.addUser(user);
        Assertions.assertEquals(user.getUsername(), userService.findUserByUsername("username").getUsername());
    }

    @Test
    void testFindUserByUsername() {
        Assertions.assertEquals("user", userService.findUserByUsername("user").getUsername());
    }
}