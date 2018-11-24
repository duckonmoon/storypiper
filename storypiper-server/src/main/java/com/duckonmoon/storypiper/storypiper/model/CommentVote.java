package com.duckonmoon.storypiper.storypiper.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class CommentVote extends BaseModel {
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private boolean vote;
    @ManyToOne
    private Comment comment;
}
