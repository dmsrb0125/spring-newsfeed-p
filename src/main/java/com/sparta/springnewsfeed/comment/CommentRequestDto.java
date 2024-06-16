package com.sparta.springnewsfeed.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@Value
public class CommentRequestDto {
    private String commentContents;
}
