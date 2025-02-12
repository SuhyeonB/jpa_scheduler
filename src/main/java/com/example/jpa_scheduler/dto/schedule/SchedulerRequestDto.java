package com.example.jpa_scheduler.dto.schedule;

import lombok.Getter;

@Getter
public class SchedulerRequestDto {
    private final String title;
    private final Long memberId;
    private final String contents;

    public SchedulerRequestDto(String title, Long memberId, String contents) {
        this.title = title;
        this.memberId = memberId;
        this.contents = contents;
    }
}
