package com.speechtotext.serviceImpl;

import com.speechtotext.errorMessages.ErrorMessages;
import com.speechtotext.exeptions.WrongIdException;
import com.speechtotext.models.Notes;
import com.speechtotext.repositories.NoteRepo;
import com.speechtotext.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class NoteServiceImp implements NoteService {

    private final NoteRepo noteRepo;
    @Override
    public List<Notes> getAllNotes() {
        return noteRepo.findAll();
    }

    @Override
    public Notes getNoteById(String id) {
        return noteRepo.findById(id)
                .orElseThrow(()-> new WrongIdException(ErrorMessages.NOTE_NOT_FOUND_BY_ID));
    }
}
