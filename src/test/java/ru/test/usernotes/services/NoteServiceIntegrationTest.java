package ru.test.usernotes.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.test.usernotes.SpringTest;
import ru.test.usernotes.entities.Note;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

class NoteServiceIntegrationTest extends SpringTest {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    NoteServiceImpl noteService;

    @Test
    void testFindNoteById() {
        Assertions.assertEquals(1L, (long)noteService.findNoteById(1).getUserId());
    }

    @Test
    void testFindNotesByUserIdAndDescription() {
        assertThat(noteService.findNotesByUserIdAndDescription(1, null), hasSize(3));
    }

    @Test
    void testAddNote() {
        Note note = new Note();
        note.setDescription("desc test");
        note.setUserId(1L);
        noteService.addNote(note);
        Assertions.assertEquals("desc test", noteService.findNotesByUserIdAndDescription(1, "desc test").get(0).getDescription());
    }

    @Test
    void testDeleteNote() {
        noteService.deleteNote(3);
        Assertions.assertEquals(2, noteService.findNotesByUserIdAndDescription(1, "").size());
    }
}