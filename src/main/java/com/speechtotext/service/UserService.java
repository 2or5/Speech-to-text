package com.speechtotext.service;

import com.speechtotext.DTO.UserDto;
import com.speechtotext.models.User;

import java.util.List;
public interface UserService {

    List<User> getAllUsers();

    User createUser(UserDto userDto);

    User getUserById(String Id);

    String deleteUserById(String Id);
}
