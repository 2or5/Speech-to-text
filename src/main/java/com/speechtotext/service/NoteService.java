package com.speechtotext.service;

import com.speechtotext.DTO.NotesDto;
import com.speechtotext.models.Notes;

import java.util.List;

public interface NoteService {
    List<Notes> getAllNotes();

    Notes getNoteById(String id);

    void saveNotes(NotesDto notesDto);

    String deleteNote(String id);
}
