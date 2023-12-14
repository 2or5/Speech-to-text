package com.speechtotext.serviceImpl;

import com.google.cloud.speech.v1.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.speechtotext.DTO.NotesDto;
import com.speechtotext.SpeechToTextApplication;
import com.speechtotext.errorMessages.ErrorMessages;
import com.speechtotext.exeptions.WrongIdException;
import com.speechtotext.models.Notes;
import com.speechtotext.models.User;
import com.speechtotext.repositories.NoteRepo;
import com.speechtotext.repositories.UserRepo;
import com.speechtotext.service.NoteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteServiceImp implements NoteService {

    private final NoteRepo noteRepo;
    private final UserRepo userRepo;
    private final Storage storage;
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
        Notes notes = Notes.builder()
                .name(notesDto.getName())
                .date(Timestamp.valueOf(LocalDateTime.now()))
                .text(convertAudioToText(notesDto.getBase64()))
                .build();
        noteRepo.save(notes);
        user.getNotes().add(notes);
        userRepo.save(user);
    }

    /**
     This method is needed to save base64 on Google bucket and after that
     use method convertAudioToText.
     **/
    private void saveAudioOnGoogleCloudBucket(String base64){
        BlobId blobId = BlobId.of("wgebrehnbrethnj4retn", "record.mp3");
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        System.out.println(base64);
        base64 = base64.trim();
        byte[] decodedByte = null;
        try {
            decodedByte = Base64.getDecoder().decode(base64.split("," )[1]);
        } catch(Exception e) {
            e.printStackTrace();
        }
        storage.create(blobInfo, decodedByte);
    }

    /**
     This method is needed to translate sound into text
     **/
    private String convertAudioToText(String base64) {
        saveAudioOnGoogleCloudBucket(base64);
        try (SpeechClient speechClient = SpeechClient.create()) {
            String gcsUri = "gs://wgebrehnbrethnj4retn/record.mp3";
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.MP3)
                            .setSampleRateHertz(16000)
                            .setLanguageCode("en-US")
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());
                return alternative.getTranscript();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ErrorMessages.CANT_TRANSLATE_AUDIO;
    }

    public List<Notes> getAllNotesByUserId() {
        Optional<User> userOptional = userRepo.findById("65549678dcc07762fc7b5200");
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
