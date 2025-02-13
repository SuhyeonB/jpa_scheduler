package com.example.jpa_scheduler.dto.comment;

import com.example.jpa_scheduler.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final String content;
    private final LocalDateTime updatedAt;
    private final String writer;

    public CommentResponseDto(Comment comment) {
        this.content = comment.getContents();
        this.updatedAt = comment.getUpdatedAt();
        this.writer = comment.getMember().getName();
    }
}