package com.speechtotext.controllers;

import com.speechtotext.models.Notes;
import com.speechtotext.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
