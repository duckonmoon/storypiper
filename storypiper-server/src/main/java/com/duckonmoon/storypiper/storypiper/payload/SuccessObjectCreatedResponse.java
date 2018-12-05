package com.duckonmoon.storypiper.storypiper.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessObjectCreatedResponse {
    private long id;
    private String table;
}
