package com.example.jpa_scheduler.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    private final String email;

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    @Size(max = 5, message = "이름은 5글자 이하만 가능합니다.")
    private final String name;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,15}$",
            message = "비밀번호는 8~15자의 영문과 숫자를 조합해야 합니다."

    )
    private final String password;

    public SignUpRequestDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
