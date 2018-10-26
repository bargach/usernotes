package ru.test.usernotes.services;

import ru.test.usernotes.entities.User;

public interface UserService {
    void addUser(User user);
    User findUserByUsername(String username);
}
