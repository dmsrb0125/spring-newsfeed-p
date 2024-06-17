package com.sparta.springnewsfeed.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;


import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CommentRequestDto {
    @NotBlank(message = "댓글 내용은 필수 입력 값입니다.")
    String commentContents;
}
