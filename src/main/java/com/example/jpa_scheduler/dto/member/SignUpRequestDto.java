package com.example.jpa_scheduler.dto.member;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private final String email;
    private final String name;
    private final String password;

    public SignUpRequestDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
