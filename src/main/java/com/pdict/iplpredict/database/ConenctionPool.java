package com.pdict.iplpredict.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConenctionPool {
    private static HikariDataSource ds;

    static {
        Properties props = new Properties();

        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        props.setProperty("dataSource.serverName", "localhost");
        props.setProperty("dataSource.portNumber", "5432");
        props.setProperty("dataSource.user", "postgres");
        props.setProperty("dataSource.password", "123456");
        props.setProperty("dataSource.databaseName", "postgres");

        HikariConfig config = new HikariConfig(props);

        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
