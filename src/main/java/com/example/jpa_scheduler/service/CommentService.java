package com.example.jpa_scheduler.service;

import com.example.jpa_scheduler.dto.comment.CommentResponseDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(Long scheduleId, String content, HttpServletRequest request);
    List<CommentResponseDto> getComments(Long scheduleId);
    CommentResponseDto updateComment(Long commentId, String content, HttpServletRequest request);
    void deleteComment(Long commentId, HttpServletRequest request);
}
