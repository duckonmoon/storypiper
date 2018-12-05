package com.duckonmoon.storypiper.storypiper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryVote extends BaseModel {
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private boolean vote;
    @ManyToOne(fetch = FetchType.LAZY)
    private Story story;
}
