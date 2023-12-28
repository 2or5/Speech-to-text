package com.speechtotext.service;

import com.speechtotext.DTO.NoteDtoResponse;
import com.speechtotext.DTO.NotesDto;
import com.speechtotext.models.Notes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoteService {
    List<Notes> getAllNotes(Pageable pageable);
    NoteDtoResponse getNoteById(String id);
    void saveNotes(NotesDto notesDto);
    void editNotes(NotesDto notesDto);
    List<Notes> getAllNotesByUserId();
    String deleteNote(String id);
}
