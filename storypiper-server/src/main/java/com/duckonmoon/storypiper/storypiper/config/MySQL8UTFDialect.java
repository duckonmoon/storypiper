package com.duckonmoon.storypiper.storypiper.config;

import org.hibernate.dialect.MySQL8Dialect;

public class MySQL8UTFDialect extends MySQL8Dialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
