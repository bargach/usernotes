package ru.test.usernotes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value = {SpringExtension.class, MockitoExtension.class})
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:applicationContext-test.xml",
        "file:src/main/webapp/WEB-INF/spring-security.xml"})
@ActiveProfiles("test")
public abstract class SpringTest {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @BeforeEach
    protected void resetDataBase() {
        jdbcTemplate.execute("DELETE FROM notes");
        jdbcTemplate.execute("DELETE FROM users");

        jdbcTemplate.execute("INSERT INTO users (id, username, password, name) VALUES (1, 'user', 'pass', 'name')");
        jdbcTemplate.execute("INSERT INTO users (id, username, password, name) VALUES (2, '2', '2', '2')");

        jdbcTemplate.execute("INSERT INTO notes (id, description, avatar, user_id) VALUES (1, 'Note 1, User 1', 'Avatar 1', 1)");
        jdbcTemplate.execute("INSERT INTO notes (id, description, avatar, user_id) VALUES (2, 'Description 1', 'Avatar 2', 1)");
        jdbcTemplate.execute("INSERT INTO notes (id, description, avatar, user_id) VALUES (3, 'Descr 2', 'Avatar 3', 1)");
        jdbcTemplate.execute("INSERT INTO notes (id, description, avatar, user_id) VALUES (4, 'Note 1, User 2', 'Avatar 4', 2)");
    }
}