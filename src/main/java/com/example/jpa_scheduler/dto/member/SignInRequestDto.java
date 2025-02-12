package com.example.jpa_scheduler.dto.member;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String email;
    private String password;
}
