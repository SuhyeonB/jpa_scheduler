package com.example.jpa_scheduler.dto.user;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String email;
    private String password;
}
