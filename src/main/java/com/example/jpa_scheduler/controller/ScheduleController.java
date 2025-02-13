package com.example.jpa_scheduler.controller;

import com.example.jpa_scheduler.dto.schedule.ScheduleResponseDto;
import com.example.jpa_scheduler.dto.schedule.ScheduleRequestDto;
import com.example.jpa_scheduler.dto.schedule.UpdateScheduleDto;
import com.example.jpa_scheduler.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @RequestBody ScheduleRequestDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Long loggedId = (Long) session.getAttribute("loggedIn");
        ScheduleResponseDto saved = scheduleService.save(dto.getTitle(), dto.getContents(), loggedId);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        List<ScheduleResponseDto> schedules = scheduleService.findAll();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto schedule = scheduleService.findById(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<ScheduleResponseDto>> findByMember(@PathVariable Long memberId) {
        List<ScheduleResponseDto> schedules = scheduleService.findByMember(memberId);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule (@PathVariable Long id, @RequestBody UpdateScheduleDto dto,
                                                HttpServletRequest request) {
        scheduleService.updateSchedule(id, dto.getTitle(), dto.getContents(), request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, HttpServletRequest request) {
        scheduleService.deleteSchedule(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
