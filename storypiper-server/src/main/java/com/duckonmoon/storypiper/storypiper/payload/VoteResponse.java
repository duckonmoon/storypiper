package com.duckonmoon.storypiper.storypiper.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponse {
    private long id;
    private boolean isUp;
    private boolean changed;
    private boolean deleted;
}
