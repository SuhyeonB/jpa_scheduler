package com.example.jpa_scheduler.dto.user;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private final String email;
    private final String name;

    public UserResponseDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
