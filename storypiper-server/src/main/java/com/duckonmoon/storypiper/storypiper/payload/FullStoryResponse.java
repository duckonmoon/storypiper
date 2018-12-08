package com.duckonmoon.storypiper.storypiper.payload;

import com.duckonmoon.storypiper.storypiper.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullStoryResponse {
    private long id;
    private String title;
    private String intro;
    private String text;
    private Status status;
    private String lastUpdatedBy;
    private Set<String> users;
    private long likes;
    private long dislikes;
    private List<FullStoryVersion> storyVersions;
    private List<FullStoryComment> comments;
}
