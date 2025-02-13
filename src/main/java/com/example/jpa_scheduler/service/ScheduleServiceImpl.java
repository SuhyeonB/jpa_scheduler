package com.example.jpa_scheduler.service;

import com.example.jpa_scheduler.dto.schedule.ScheduleResponseDto;
import com.example.jpa_scheduler.entity.Member;
import com.example.jpa_scheduler.entity.Schedule;
import com.example.jpa_scheduler.repository.MemberRepository;
import com.example.jpa_scheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto save(String title, String contents, Long memberId) {
        Schedule schedule = new Schedule(title, contents);

        Member findMember = memberRepository.findMemberByIdOrElseThrow(memberId);
        schedule.setMember(findMember);

        Schedule saved = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(saved.getTitle(), findMember.getName(), saved.getContents(),
                saved.getCreatedAt(), saved.getUpdatedAt());
    }

    @Override
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    @Override
    public ScheduleResponseDto findById(Long id) {
        Schedule find = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(find.getTitle(), find.getMember().getName(), find.getContents(),
                find.getCreatedAt(), find.getUpdatedAt());
    }

    @Override
    public List<ScheduleResponseDto> findByMember(Long memberId) {
        List<Schedule> schedules = scheduleRepository.findByMember_Id(memberId);
        return schedules.stream().map(ScheduleResponseDto::toDto)
                .toList();
    }

    @Override
    public void updateSchedule(Long id, String title, String contents) {
        Schedule find = scheduleRepository.findByIdOrElseThrow(id);

        // user가 로그인 되어 있는가 (chain)
        // 로그인된 사용자가 작성자와 같은가?
        if (!logged.isequal()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "does not match with writer");
        }

        find.updateSchedule(title, contents);
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule find = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(find);
    }
}
