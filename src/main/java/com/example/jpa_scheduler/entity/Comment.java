package com.example.jpa_scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment() {}

    public Comment(String contents, Member member, Schedule schedule) {
        this.contents = contents;
        this.member = member;
        this.schedule = schedule;
    }

    public void updateContent(String contents) {
        this.contents = contents;
    }
}
