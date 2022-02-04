package com.jumia.exercise.config;

import org.hibernate.dialect.Dialect;

import static java.sql.Types.*;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(INTEGER, "integer");
        registerColumnType(VARCHAR, "varchar");
    }
}
