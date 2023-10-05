package com.speechtotext.repositories;

import com.speechtotext.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
}
