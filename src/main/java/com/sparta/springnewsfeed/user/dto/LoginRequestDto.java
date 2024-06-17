package com.sparta.springnewsfeed.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Value
public class LoginRequestDto {
    @NotBlank(message = "사용자 ID는 필수 입력 값입니다.")
    String userId;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    String password;
}