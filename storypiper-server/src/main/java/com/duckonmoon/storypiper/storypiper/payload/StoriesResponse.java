package com.duckonmoon.storypiper.storypiper.payload;

import lombok.Data;

import java.util.List;

@Data
public class StoriesResponse {
    private List<ShortStory> stories;
}
