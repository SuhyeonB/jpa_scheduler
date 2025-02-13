package com.example.jpa_scheduler.service;

import com.example.jpa_scheduler.dto.member.SignInResponseDto;
import com.example.jpa_scheduler.dto.member.MemberResponseDto;
import com.example.jpa_scheduler.entity.Member;
import com.example.jpa_scheduler.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void signUp(String email, String name, String password) {
        Member member = new Member(email, name, password);
        memberRepository.save(member);
    }

    @Override
    public SignInResponseDto signIn(String email, String password) {
        Member signedMember = memberRepository.findMemberByEmailOrElseThrow(email);
        if (!signedMember.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match");
        }
        // session 설정
        return new SignInResponseDto(signedMember.getId(), signedMember.getName());
    }

    @Override
    public MemberResponseDto findMemberById(Long id) {
        Member findMember = memberRepository.findMemberByIdOrElseThrow(id);
        return new MemberResponseDto(findMember.getEmail(), findMember.getName());
    }

    @Override
    public MemberResponseDto updateMember(Long id, String name, String password) {
        Member updatedMember = memberRepository.findMemberByIdOrElseThrow(id);
        if (!updatedMember.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match");
        }
        updatedMember.updateMember(name, password);
        return null;
    }

    @Override
    public void deleteMember(Long id) {
        Member findMember = memberRepository.findMemberByIdOrElseThrow(id);
        memberRepository.delete(findMember);
    }
}
