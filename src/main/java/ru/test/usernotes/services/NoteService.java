package ru.test.usernotes.services;

import ru.test.usernotes.entities.Note;

import java.util.List;

public interface NoteService {
    Note findNoteById(long id);
    List<Note> findNotesByUserIdAndDescription(long userId, String description);
    void addNote(Note note);
    void deleteNote(long noteId);
}
