package com.duckonmoon.storypiper.storypiper.payload;

import com.duckonmoon.storypiper.storypiper.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullStoryComment {
    private String text;
    private Status status;
}
