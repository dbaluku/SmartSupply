package org.smartsupply.api.service.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnPool {

    private HikariDataSource ds = null;

    public HikariConnPool(String driverClassName, String className, String url, String userName, String password) {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(50);
//        config.setDataSourceClassName(className);
        config.setUsername(userName);
        config.setJdbcUrl(url);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);

        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
