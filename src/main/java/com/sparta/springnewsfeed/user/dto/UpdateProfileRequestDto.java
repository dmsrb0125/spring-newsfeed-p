package com.sparta.springnewsfeed.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class UpdateProfileRequestDto {

    @Size(max = 10, message = "이름은 최대 10글자까지 입력 가능합니다.")
    private String name;

    @Size(max = 20, message = "한줄소개는 최대 20글자까지 입력 가능합니다.")
    private String introduction;
}
