package com.sparta.springnewsfeed.user.dto;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserInquiryResponseDto {

    private String userId;
    private String name;
    private String introduction;
    private String email;
    private String pictureURL;
}
