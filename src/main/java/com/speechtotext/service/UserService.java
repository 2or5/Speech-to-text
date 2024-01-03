package com.speechtotext.service;

import com.speechtotext.models.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface UserService {

    List<User> getAllUsers(Pageable pageable);

    User getUserById(String Id);

    String deleteUserById(String Id);
}
