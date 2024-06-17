package com.sparta.springnewsfeed.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;


@Value
public class PostRequest {
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Content is mandatory")
    private String content;
}