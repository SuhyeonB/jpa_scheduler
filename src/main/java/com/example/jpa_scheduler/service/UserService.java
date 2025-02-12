package com.example.jpa_scheduler.service;

import com.example.jpa_scheduler.dto.user.SignInResponseDto;
import com.example.jpa_scheduler.dto.user.UserResponseDto;

public interface UserService {
    void signUp(String email, String name, String password);
    SignInResponseDto signIn(String email, String password);
    UserResponseDto findUserById(Long id);
    UserResponseDto updateUser(Long id, String name, String password);
    void deleteUser(Long id);
}
