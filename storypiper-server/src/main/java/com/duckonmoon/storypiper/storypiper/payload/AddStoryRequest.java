package com.duckonmoon.storypiper.storypiper.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddStoryRequest {
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String title;
    @NotNull
    @NotBlank
    @Size(min = 10)
    private String intro;
    @NotNull
    @NotBlank
    @Size(min = 10)
    private String text;
}
