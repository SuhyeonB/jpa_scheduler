package com.example.jpa_scheduler.service;

import com.example.jpa_scheduler.dto.schedule.ScheduleResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto save(String title, String contents, Long memberId);
    List<ScheduleResponseDto> findAll();
    Page<ScheduleResponseDto> findAllPaged(int size);
    ScheduleResponseDto findById(Long id);
    List<ScheduleResponseDto> findByMember(Long memberId);
    //void updateSchedule(Long id, String title, String contents);
    void updateSchedule(Long id, String title, String contents, HttpServletRequest request);

    void deleteSchedule(Long id, HttpServletRequest request);
}
