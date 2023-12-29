package com.speechtotext.controllers;

import com.speechtotext.service.UserService;
import com.speechtotext.DTO.UserDto;
import com.speechtotext.models.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UsersControllers {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllNotes(Pageable pageable) {
        List<User> users = userService.getAllUsers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<User> getUserById(@PathVariable String Id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(Id));
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        User createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/edit-user") ///////TODO
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto){
        User createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdUser);
    }

    @DeleteMapping("/delete-user/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserById(Id));
    }

}
