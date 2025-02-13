package com.example.jpa_scheduler.controller;

import com.example.jpa_scheduler.dto.comment.CommentRequestDto;
import com.example.jpa_scheduler.dto.comment.CommentResponseDto;
import com.example.jpa_scheduler.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long postId, @RequestBody CommentRequestDto dto, HttpServletRequest request) {
        CommentResponseDto response = commentService.createComment(postId, dto.getContents(), request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findCommentsOfSchedule(@PathVariable Long postId) {
        List<CommentResponseDto> comments = commentService.getComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PatchMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long postId, @PathVariable Long id, @RequestBody CommentRequestDto dto,
            HttpServletRequest request) {
        CommentResponseDto updated = commentService.updateComment(id, dto.getContents(), request);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long postId, @PathVariable Long id, HttpServletRequest request) {
        commentService.deleteComment(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}