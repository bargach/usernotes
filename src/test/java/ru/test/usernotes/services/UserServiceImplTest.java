package ru.test.usernotes.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.test.usernotes.entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Test
    void testFindUserByUsername() {
        UserServiceImpl userService = new UserServiceImpl(bCryptPasswordEncoder, namedParameterJdbcTemplate);
        User user = new User();
        Mockito.doReturn(user).when(namedParameterJdbcTemplate)
                .queryForObject(anyString(), any(MapSqlParameterSource.class), any(BeanPropertyRowMapper.class));
        Assertions.assertEquals(user, userService.findUserByUsername("username"));
    }

    @Test
    void testAddUser() {
        UserServiceImpl userService = new UserServiceImpl(bCryptPasswordEncoder, namedParameterJdbcTemplate);
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setName("name");
        userService.addUser(user);
        ArgumentCaptor<MapSqlParameterSource> captor = ArgumentCaptor.forClass(MapSqlParameterSource.class);
        Mockito.verify(namedParameterJdbcTemplate).update(
                eq("INSERT INTO users (username, name, password) VALUES (:username, :name, :password)"),
                captor.capture());
        assertEquals(user.getUsername(), captor.getValue().getValue("username"));
        assertTrue(bCryptPasswordEncoder.matches(user.getPassword(), captor.getValue().getValue("password").toString()));
        assertEquals(user.getName(), captor.getValue().getValue("name"));
    }
}