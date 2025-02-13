package com.example.jpa_scheduler.controller;

import com.example.jpa_scheduler.dto.member.*;
import com.example.jpa_scheduler.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignUpRequestDto dto) {
        memberService.signUp(dto.getEmail(), dto.getName(), dto.getPassword());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signin(@RequestBody SignInRequestDto dto, HttpServletRequest request) {
        SignInResponseDto signInResponseDto = memberService.signIn(dto.getEmail(), dto.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute("loggedIn", signInResponseDto.getId());
        return new ResponseEntity<>(signInResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> showMemberInfo(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findMemberById(id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        // session 제거
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Long id, @RequestBody UpdateRequestDto dto) {
        MemberResponseDto memberResponseDto = memberService.updateMember(id, dto.getName(), dto.getPassword());

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
