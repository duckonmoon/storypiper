package com.duckonmoon.storypiper.storypiper.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Story extends AbstractUpdatable {
    private String title;
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
