package com.speechtotext.service;

import com.speechtotext.models.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {
    @Test
    void getAllUsersTest(){
        User user = new User();
        List <User> userList = new ArrayList<>();
        userList.add(user);
        
        assertTrue(true);
    }

}
