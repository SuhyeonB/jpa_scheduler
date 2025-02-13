package com.example.jpa_scheduler.dto.schedule;

import lombok.Getter;

@Getter
public class UpdateScheduleDto {
    private final String title;
    private final String contents;

    public UpdateScheduleDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
