package com.example.jpa_scheduler.service;

import com.example.jpa_scheduler.dto.schedule.ScheduleResponseDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto save(String title, String contents, Long memberId);
    List<ScheduleResponseDto> findAll();
    ScheduleResponseDto findById(Long id);
    List<ScheduleResponseDto> findByMember(Long memberId);
    //void updateSchedule(Long id, String title, String contents);
    void updateSchedule(Long id, String title, String contents, HttpServletRequest request);

    void deleteSchedule(Long id, HttpServletRequest request);
}
