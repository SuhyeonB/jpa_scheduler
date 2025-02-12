package com.example.jpa_scheduler.controller;

import com.example.jpa_scheduler.dto.schedule.ScheduleResponseDto;
import com.example.jpa_scheduler.dto.schedule.SchedulerRequestDto;
import com.example.jpa_scheduler.dto.schedule.UpdateScheduleDto;
import com.example.jpa_scheduler.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody SchedulerRequestDto dto) {
        scheduleService.save(dto.getTitle(), dto.getContents(), dto.getMemberId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        scheduleService.findAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        scheduleService.findById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<ScheduleResponseDto>> findByMember(@PathVariable Long memberId) {
        scheduleService.findByMember(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule (@PathVariable Long id, @RequestBody UpdateScheduleDto dto) {
        scheduleService.updateSchedule(id, dto.getTitle(), dto.getContents());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
