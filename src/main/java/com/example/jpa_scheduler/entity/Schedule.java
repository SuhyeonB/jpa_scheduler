package com.example.jpa_scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    // user name
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @Setter
    private Member member;

    public Schedule(){}
    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void updateSchedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
