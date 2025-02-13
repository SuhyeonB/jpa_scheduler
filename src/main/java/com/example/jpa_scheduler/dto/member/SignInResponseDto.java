package com.example.jpa_scheduler.dto.member;

import lombok.Getter;

@Getter
public class SignInResponseDto {
    private final Long id;
    private final String name;

    public SignInResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
