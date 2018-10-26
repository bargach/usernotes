package ru.test.usernotes.services;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ru.test.usernotes.entities.Note;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NoteServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Note findNoteById(long id) {
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM notes WHERE id = :id",
                new MapSqlParameterSource("id", id), new BeanPropertyRowMapper<>(Note.class));
    }

    @Override
    public List<Note> findNotesByUserIdAndDescription(long userId, String description) {
        String sql = "SELECT * FROM notes WHERE user_id = :userId " +
                (description == null ? "" : " AND description LIKE CONCAT('%', :description, '%')") +
                " ORDER BY id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("userId", userId);
        if (description != null)
            mapSqlParameterSource.addValue("description", description);

        return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, new BeanPropertyRowMapper<>(Note.class));
    }

    @Override
    public void addNote(Note note) {
        namedParameterJdbcTemplate.update(
                "INSERT INTO notes (description, avatar, user_id) VALUES (:description, :avatar, :userId)",
                new MapSqlParameterSource("description", note.getDescription())
                        .addValue("avatar", note.getAvatar())
                        .addValue("userId", note.getUserId()));
    }

    @Override
    public void deleteNote(long noteId) {
        namedParameterJdbcTemplate.update("DELETE FROM notes WHERE id = :noteId",
                new MapSqlParameterSource("noteId", noteId));
    }
}
