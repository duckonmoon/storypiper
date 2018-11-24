package com.duckonmoon.storypiper.storypiper.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class AbstractUpdatable extends BaseModel {
    private Date createdAt;
    private Date lastUpdatedAt;
    @OneToOne(fetch = FetchType.LAZY)
    private User createdBy;
}
