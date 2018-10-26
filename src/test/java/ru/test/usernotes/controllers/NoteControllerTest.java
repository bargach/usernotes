package ru.test.usernotes.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import ru.test.usernotes.SpringMVCTest;
import ru.test.usernotes.services.NoteService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NoteControllerTest extends SpringMVCTest {
    @Autowired
    NoteService noteService;

    @Test
    @WithMockUser
    void userNotes() throws Exception {
        mockMvc.perform(get("/notes"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("notes", hasSize(3)))
                .andExpect(view().name("notes"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    void searchNotes() throws Exception {
        mockMvc.perform(get("/notes?search=Note"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("notes", hasSize(1)))
                .andExpect(view().name("notes"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    void searchNotesEmpty() throws Exception {
        mockMvc.perform(get("/?search="))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("notes", hasSize(3)))
                .andExpect(view().name("notes"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    void addNote() throws Exception {
        mockMvc.perform(post("/notes/add").param("description", "added note"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/notes"))
                .andDo(print());
        assertThat(noteService.findNotesByUserIdAndDescription(1, "added note"), hasSize(1));
    }

    @Test
    @WithMockUser
    void deleteNote() throws Exception {
        mockMvc.perform(post("/notes/{id}/delete", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/notes"))
                .andDo(print());
    }
}