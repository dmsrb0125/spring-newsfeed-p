package com.sparta.springnewsfeed.user.dto;

import lombok.*;

@Value
@Setter
@Getter
public class LoginRequestDto {
    private String userId;
    private String password;
}