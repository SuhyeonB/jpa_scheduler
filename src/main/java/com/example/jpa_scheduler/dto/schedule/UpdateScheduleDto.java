package com.example.jpa_scheduler.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleDto {
    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(max = 10, message = "제목은 최대 10글자까지 입력할 수 있습니다.")
    private final String title;

    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private final String contents;

    public UpdateScheduleDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
