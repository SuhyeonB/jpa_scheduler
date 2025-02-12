package com.example.jpa_scheduler.service;

import com.example.jpa_scheduler.dto.schedule.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto save(String title, String contents, Long memberId);
    List<ScheduleResponseDto> findAll();
    ScheduleResponseDto findById(Long id);
    List<ScheduleResponseDto> findByMember(Long memberId);
    void updateSchedule(Long id, String title, String contents);
    void deleteSchedule(Long id);
}
