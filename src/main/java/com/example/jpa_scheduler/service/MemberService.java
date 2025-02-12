package com.example.jpa_scheduler.service;

import com.example.jpa_scheduler.dto.member.SignInResponseDto;
import com.example.jpa_scheduler.dto.member.MemberResponseDto;

public interface MemberService {
    void signUp(String email, String name, String password);
    SignInResponseDto signIn(String email, String password);
    MemberResponseDto findMemberById(Long id);
    MemberResponseDto updateMember(Long id, String name, String password);
    void deleteMember(Long id);
}
