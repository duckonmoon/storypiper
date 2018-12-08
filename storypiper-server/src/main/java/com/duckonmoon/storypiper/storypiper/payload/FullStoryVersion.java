package com.duckonmoon.storypiper.storypiper.payload;

import com.duckonmoon.storypiper.storypiper.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullStoryVersion {
    private Long id;
    private Status status;
    private String createdAt;
    private String createdBy;
}
