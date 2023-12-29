package com.speechtotext.repositories;

import com.speechtotext.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User,String> {
    Optional<User>findUserByEmail(String username);
}
