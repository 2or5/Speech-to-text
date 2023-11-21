package com.speechtotext.serviceImpl;

import com.speechtotext.DTO.NotesDto;
import com.speechtotext.errorMessages.ErrorMessages;
import com.speechtotext.exeptions.WrongIdException;
import com.speechtotext.models.Notes;
import com.speechtotext.models.User;
import com.speechtotext.repositories.NoteRepo;
import com.speechtotext.repositories.UserRepo;
import com.speechtotext.service.NoteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteServiceImp implements NoteService {

    private final NoteRepo noteRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
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
        User user = userRepo.findById(notesDto.getUserId())
                .orElseThrow(()-> new WrongIdException(ErrorMessages.USER_NOT_FOUND_BY_ID));
        Notes newNotes = modelMapper.map(notesDto, Notes.class);
        newNotes.setDate(Timestamp.valueOf(LocalDateTime.now()));
        noteRepo.save(newNotes);
        user.setNotes(List.of(newNotes));
        userRepo.save(user);
    }

    public List<Notes> getAllNotesByUserId() {
        Optional<User> userOptional = userRepo.findById("651c61e6de1460284ddef65b");
        // Обробка випадку, коли користувача не знайдено
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getNotes();
        } else return Collections.emptyList();
    }

    @Override
    public String deleteNote(String id) {
        noteRepo.deleteById(id);
        return "Note was deleted:" + id;
    }
}
