package com.duckonmoon.storypiper.storypiper.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Comment extends AbstractUpdatable {
    private String text;
    private Status status;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
    private List<CommentVote> votes;
    @ManyToOne(fetch = FetchType.LAZY)
    private Story story;
}
