package ru.test.usernotes.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.test.usernotes.entities.Note;
import ru.test.usernotes.entities.User;
import ru.test.usernotes.services.AvatarServiceImpl;
import ru.test.usernotes.services.NoteService;
import ru.test.usernotes.services.UserService;

import java.io.IOException;

@Controller
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;
    private final AvatarServiceImpl avatarService;

    public NoteController(NoteService noteService, UserService userService, AvatarServiceImpl avatarService) {
        this.noteService = noteService;
        this.userService = userService;
        this.avatarService = avatarService;
    }

    @GetMapping(value = {"/notes", "/"})
    public ModelAndView notes(Authentication authentication, String search) {
        User currentUser = userService.findUserByUsername(authentication.getName());

        return new ModelAndView("notes")
                .addObject("notes", noteService.findNotesByUserIdAndDescription(currentUser.getId(), search));
    }

    @GetMapping(value = "/notes/{id}")
    public ModelAndView noteDetails(@PathVariable long id, Authentication authentication) {
        User currentUser = userService.findUserByUsername(authentication.getName());
        Note note = noteService.findNoteById(id);
        if (!note.getUserId().equals(currentUser.getId()))
            throw new IllegalStateException("Note from another user");

        return new ModelAndView("note_details")
                .addObject("note", noteService.findNoteById(id));
    }

    @GetMapping(value = "/notes/add")
    public String addNote() {
        return "add_note";
    }

    @PostMapping(value = "/notes/add")
    public String addNote(String description, Authentication authentication) throws IOException {
        User currentUser = userService.findUserByUsername(authentication.getName());

        Note note = new Note();
        note.setDescription(description);
        note.setAvatar(avatarService.downloadAvatar());
        note.setUserId(currentUser.getId());
        noteService.addNote(note);
        return "redirect:/notes";
    }

    @PostMapping(value = "/notes/{id}/delete")
    public String deleteNote(@PathVariable long id, Authentication authentication) {
        User currentUser = userService.findUserByUsername(authentication.getName());
        Note note = noteService.findNoteById(id);
        if (!note.getUserId().equals(currentUser.getId()))
            throw new IllegalStateException("Note from another user");

        noteService.deleteNote(id);
        return "redirect:/notes";
    }
}
