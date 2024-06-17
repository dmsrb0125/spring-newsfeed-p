package com.sparta.springnewsfeed.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class EmailVerificationRequestDto {

    @NotBlank(message = "인증 코드를 입력해주세요.")
    private String code;
}
