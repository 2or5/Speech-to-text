package com.speechtotext.serviceImpl;

import com.speechtotext.DTO.NotesDto;
import com.speechtotext.errorMessages.ErrorMessages;
import com.speechtotext.exeptions.WrongIdException;
import com.speechtotext.models.Notes;
import com.speechtotext.repositories.NoteRepo;
import com.speechtotext.service.NoteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class NoteServiceImp implements NoteService {

    private final NoteRepo noteRepo;
    private final ModelMapper modelMapper;
    @Override
    public List<Notes> getAllNotes() {
        return noteRepo.findAll();
    }

    @Override
    public Notes getNoteById(String id) {
        return noteRepo.findById(id)
                .orElseThrow(()-> new WrongIdException(ErrorMessages.NOTE_NOT_FOUND_BY_ID));
    }

    @Override
    public void saveNotes(NotesDto notesDto) {
        Notes newNotes = modelMapper.map(notesDto, Notes.class);
        newNotes.setDate(Timestamp.valueOf(LocalDateTime.now()));
        noteRepo.save(newNotes);
    }

    @Override
    public String deleteNote(String id) {
        noteRepo.deleteById(id);
        return "Note was deleted:" + id;
    }
}
