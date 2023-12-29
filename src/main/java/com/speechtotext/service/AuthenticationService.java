package com.speechtotext.service;

import com.speechtotext.DTO.AuthenticationRequestDto;
import com.speechtotext.DTO.AuthenticationResponseDto;
import com.speechtotext.DTO.RegisterRequestDto;

public interface AuthenticationService {

    AuthenticationResponseDto register(RegisterRequestDto request);
    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);
}
