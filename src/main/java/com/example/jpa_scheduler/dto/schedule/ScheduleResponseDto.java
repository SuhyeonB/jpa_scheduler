package com.example.jpa_scheduler.dto.schedule;

import com.example.jpa_scheduler.entity.Schedule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final String title;
    private final String memberName;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleResponseDto(String title, String memberName, String contents,
                               LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.memberName = memberName;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ScheduleResponseDto toDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getTitle(), schedule.getMember().getName(),
                schedule.getContents(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }
}
