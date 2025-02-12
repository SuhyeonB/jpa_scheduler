package com.example.jpa_scheduler.controller;

import com.example.jpa_scheduler.dto.user.*;
import com.example.jpa_scheduler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignUpRequestDto dto) {
        userService.signUp(dto.getEmail(), dto.getName(), dto.getPassword());
        // redirect
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signin(@RequestBody SignInRequestDto dto) {
        SignInResponseDto signInResponseDto = userService.signIn(dto.getEmail(), dto.getPassword());
        return new ResponseEntity<>(signInResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> showUserInfo(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findUserById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(){
        // session 제거
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UpdateRequestDto dto) {
        UserResponseDto userResponseDto = userService.updateUser(id, dto.getName(), dto.getPassword());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
