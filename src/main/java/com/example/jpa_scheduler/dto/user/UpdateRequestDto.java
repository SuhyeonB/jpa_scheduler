package com.example.jpa_scheduler.dto.user;

import lombok.Getter;

@Getter
public class UpdateRequestDto {
    private final String name;
    private final String password;

    public UpdateRequestDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
