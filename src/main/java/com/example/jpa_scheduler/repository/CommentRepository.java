package com.example.jpa_scheduler.repository;

import com.example.jpa_scheduler.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
