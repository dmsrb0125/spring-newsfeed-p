package com.sparta.springnewsfeed.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@Value
public class PostRequest {

    @NotBlank
    private String title;

    @NotNull
    private String content;
}
