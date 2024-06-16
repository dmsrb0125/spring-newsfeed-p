package com.sparta.springnewsfeed.user.dto;

import lombok.*;

@Value
public class LoginRequestDto {
    String userId;
    String password;
}