package com.duckonmoon.storypiper.storypiper.payload;

import lombok.Data;

@Data
public class ShortStory {
    private Long id;
    private String title;
    private String intro;
    private String createdBy;
    private long likes;
    private long dislikes;
}
