package com.duckonmoon.storypiper.storypiper.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class StoryVersion extends BaseModel {
    private String title;
    @Column(columnDefinition = "TEXT")
    private String intro;
    @Column(columnDefinition = "TEXT")
    private String text;
    private Status status;
    private Date createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;
    @ManyToOne(fetch = FetchType.LAZY)
    private Story story;
}
