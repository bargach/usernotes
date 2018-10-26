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
import ru.test.usernotes.entities.Note;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    void testFindNotesByUserId() {
        NoteServiceImpl noteService = new NoteServiceImpl(namedParameterJdbcTemplate);
        Mockito.doReturn(singletonList(new Note())).when(namedParameterJdbcTemplate)
                .query(anyString(), any(MapSqlParameterSource.class), any(BeanPropertyRowMapper.class));
        Assertions.assertNotNull(noteService.findNotesByUserIdAndDescription(1, null));
    }

    @Test
    void testDeleteNote() {
        NoteServiceImpl noteService = new NoteServiceImpl(namedParameterJdbcTemplate);
        noteService.deleteNote(1);
        ArgumentCaptor<MapSqlParameterSource> captor = ArgumentCaptor.forClass(MapSqlParameterSource.class);
        Mockito.verify(namedParameterJdbcTemplate).update(eq("DELETE FROM notes WHERE id = :noteId"), captor.capture());
        assertEquals(1, (long)captor.getValue().getValue("noteId"));
    }
}