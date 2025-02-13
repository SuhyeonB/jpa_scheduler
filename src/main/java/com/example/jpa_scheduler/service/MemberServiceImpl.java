package com.example.jpa_scheduler.service;

import com.example.jpa_scheduler.config.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(String email, String name, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Member member = new Member(email, name, encodedPassword);
        memberRepository.save(member);
    }

    @Override
    public SignInResponseDto signIn(String email, String password) {
        Member signedMember = memberRepository.findMemberByEmailOrElseThrow(email);
        if (!passwordEncoder.matches(password, signedMember.getPassword())) {
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
        if (!passwordEncoder.matches(password, updatedMember.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match");
        }
        String newEncodedPassword = passwordEncoder.encode(password);
        updatedMember.updateMember(name, newEncodedPassword);

        return new MemberResponseDto(updatedMember.getEmail(), updatedMember.getName());
    }

    @Override
    public void deleteMember(Long id) {
        Member findMember = memberRepository.findMemberByIdOrElseThrow(id);
        memberRepository.delete(findMember);
    }
}
