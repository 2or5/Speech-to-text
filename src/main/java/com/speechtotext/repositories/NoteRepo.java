package com.speechtotext.repositories;

import com.speechtotext.models.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepo extends MongoRepository<Notes, String> {
}
