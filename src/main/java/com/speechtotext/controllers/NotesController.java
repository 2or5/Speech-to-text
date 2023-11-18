package com.speechtotext.controllers;

import com.speechtotext.DTO.NotesDto;
import com.speechtotext.models.Notes;
import com.speechtotext.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NotesController {

    private final NoteService noteService;
    @GetMapping
    public ResponseEntity<List<Notes>> getAllNotes() {
        List<Notes> notes = noteService.getAllNotes();
        return ResponseEntity.status(HttpStatus.OK).body(notes);
    }

    @GetMapping("/note/{Id}")
    public ResponseEntity<Notes> getNoteById(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getNoteById(Id));
    }

    @PostMapping("/create-note")
    public ResponseEntity<Notes> createNote(@RequestBody NotesDto notesDto){
        noteService.saveNotes(notesDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
