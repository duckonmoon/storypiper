package com.duckonmoon.storypiper.storypiper.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Story extends AbstractUpdatable {
    private String title;
    @Column(columnDefinition = "TEXT")
    private String intro;
    @Column(columnDefinition = "TEXT")
    private String text;
    private Status status;
    @OneToOne(fetch = FetchType.LAZY)
    private User lastUpdatedBy;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "story")
    private List<Comment> comments;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "story")
    private List<StoryVote> votes;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "story")
    private List<StoryVersion> storyVersions;
}
