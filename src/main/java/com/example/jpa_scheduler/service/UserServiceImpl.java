package com.example.jpa_scheduler.service;

import com.example.jpa_scheduler.dto.user.SignInResponseDto;
import com.example.jpa_scheduler.dto.user.UserResponseDto;
import com.example.jpa_scheduler.entity.User;
import com.example.jpa_scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public void signUp(String email, String name, String password) {
        User user = new User(email, name, password);
        userRepository.save(user);
    }

    @Override
    public SignInResponseDto signIn(String email, String password) {
        User signedUser = userRepository.findUserByEmailOrElseThrow(email);
        if (!signedUser.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match");
        }
        // session 설정
        return new SignInResponseDto(signedUser.getId(), signedUser.getName());
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User findUser = optionalUser.get();
        return new UserResponseDto(findUser.getEmail(), findUser.getName());
    }

    @Override
    public UserResponseDto updateUser(Long id, String name, String password) {
        User updatedUser = userRepository.findUserByIdOrElseThrow(id);
        if (!updatedUser.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match");
        }
        updatedUser.updateUser(name, password);
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        User findUser = userRepository.findUserByIdOrElseThrow(id);
        userRepository.delete(findUser);
    }
}
