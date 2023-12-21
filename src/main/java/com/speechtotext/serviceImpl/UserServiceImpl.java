package com.speechtotext.serviceImpl;

import com.speechtotext.DTO.UserDto;
import com.speechtotext.errorMessages.ErrorMessages;
import com.speechtotext.exeptions.WrongIdException;
import com.speechtotext.models.User;
import com.speechtotext.repositories.UserRepo;
import com.speechtotext.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<User> getAllUsers(Pageable pageable) {
        Page<User> page = userRepo.findAll(pageable);
        return page.getContent();
    }

    @Override
    public User createUser(UserDto userDto) {
        User newUser = modelMapper.map(userDto, User.class);
        return userRepo.insert(newUser);
    }

    @Override
    public User getUserById(String Id) {
        return userRepo.findById(Id)
                .orElseThrow(() -> new WrongIdException(ErrorMessages.USER_NOT_FOUND_BY_ID + Id));
    }

    @Override
    public String deleteUserById(String Id) {
        userRepo.deleteById(Id);
        return Id;
    }
}
