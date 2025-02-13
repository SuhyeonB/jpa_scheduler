package com.example.jpa_scheduler.service;

import com.example.jpa_scheduler.dto.comment.CommentResponseDto;
import com.example.jpa_scheduler.entity.Comment;
import com.example.jpa_scheduler.entity.Member;
import com.example.jpa_scheduler.entity.Schedule;
import com.example.jpa_scheduler.repository.CommentRepository;
import com.example.jpa_scheduler.repository.MemberRepository;
import com.example.jpa_scheduler.repository.ScheduleRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public CommentResponseDto createComment(Long scheduleId, String content, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        Long loggedInId = (Long) session.getAttribute("loggedIn");
        Member member = memberRepository.findMemberByIdOrElseThrow(loggedInId);
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        Comment comment = new Comment(content, member, schedule);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }

    @Override
    public List<CommentResponseDto> getComments(Long scheduleId) {
        List<Comment> comments = commentRepository.findByScheduleIdOrderByCreatedAtDesc(scheduleId);
        return comments.stream().map(CommentResponseDto::new).toList();
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(Long commentId, String content, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "need to update");
        }

        Long loggedInId = (Long) session.getAttribute("loggedIn");
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        if (!comment.getMember().getId().equals(loggedInId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "only writer can update");
        }

        comment.updateContent(content);
        return new CommentResponseDto(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "need to log in");
        }

        Long loggedInId = (Long) session.getAttribute("loggedIn");
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        if (!comment.getMember().getId().equals(loggedInId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only writer can delete");
        }

        commentRepository.delete(comment);
    }
}

