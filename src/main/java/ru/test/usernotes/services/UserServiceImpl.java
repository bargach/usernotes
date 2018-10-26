package ru.test.usernotes.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.test.usernotes.entities.User;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void addUser(User user) {
        namedParameterJdbcTemplate.update("INSERT INTO users (username, name, password) VALUES (:username, :name, :password)",
                new MapSqlParameterSource("username", user.getUsername())
                        .addValue("password", bCryptPasswordEncoder.encode(user.getPassword()))
                        .addValue("name", user.getName()));
    }

    @Override
    public User findUserByUsername(String username) {
        try {
            return namedParameterJdbcTemplate.queryForObject("SELECT * FROM users WHERE username = :username",
                    new MapSqlParameterSource("username", username), new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
