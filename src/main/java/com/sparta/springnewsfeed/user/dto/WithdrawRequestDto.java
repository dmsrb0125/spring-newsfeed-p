package com.sparta.springnewsfeed.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Value
public class WithdrawRequestDto {

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}