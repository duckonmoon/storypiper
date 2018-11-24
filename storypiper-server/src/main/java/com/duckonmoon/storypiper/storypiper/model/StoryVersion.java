package com.duckonmoon.storypiper.storypiper.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class StoryVersion extends BaseModel {
    private String title;
    private String text;
    private Status status;
    private Date createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private Story story;
}
