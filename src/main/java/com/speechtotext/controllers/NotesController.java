package com.speechtotext.controllers;

import com.speechtotext.DTO.NoteDtoResponse;
import com.speechtotext.DTO.NotesDto;
import com.speechtotext.models.Notes;
import com.speechtotext.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class NotesController {

    private final NoteService noteService;
    @GetMapping
    public ResponseEntity<List<Notes>> getAllNotes(Pageable pageable) {
        List<Notes> notes = noteService.getAllNotes(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(notes);
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<NoteDtoResponse> getNoteById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getNoteById(id));
    }

    @PostMapping("/create-note")
    public ResponseEntity<Notes> createNote(@RequestBody NotesDto notesDto){
        noteService.saveNotes(notesDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/edit-note")
    public ResponseEntity<Notes> editNote(@RequestBody NotesDto notesDto){
        noteService.editNotes(notesDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/notesss")
    public ResponseEntity<List<Notes>> getAllNotesByUserId(){
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getAllNotesByUserId());
    }

    @DeleteMapping("/delete-note/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(noteService.deleteNote(id));
    }
}
