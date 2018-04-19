package org.smartsupply.api.database;

import org.smartsupply.api.security.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;

public class MyDriverManagerDataSource extends AbstractDriverBasedDataSource {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void setPassword(String password) {
        try {
            password = Security.decrypt(password);
            super.setPassword(password);
        } catch (Exception e) {
            log.error("Fatal error connecting to db.couldn't get password", e);
            super.setPassword(password);
        }
    }

    public MyDriverManagerDataSource() {
    }

    public MyDriverManagerDataSource(String url) {
        this.setUrl(url);
    }

    public MyDriverManagerDataSource(String url, String username, String password) {
        this.setUrl(url);
        this.setUsername(username);
        this.setPassword(password);
    }

    public MyDriverManagerDataSource(String url, Properties conProps) {
        this.setUrl(url);
        this.setConnectionProperties(conProps);
    }

    public void setDriverClassName(String driverClassName) {
        Assert.hasText(driverClassName, "Property \'driverClassName\' must not be empty");
        String driverClassNameToUse = driverClassName.trim();

        try {
            Class.forName(driverClassNameToUse, true, ClassUtils.getDefaultClassLoader());
        } catch (ClassNotFoundException var4) {
            throw new IllegalStateException("Could not load JDBC driver class [" + driverClassNameToUse + "]", var4);
        }

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Loaded JDBC driver: " + driverClassNameToUse);
        }

    }

    public void setConnectionProperties(Properties props) {
        super.setConnectionProperties(props);
    }

    protected Connection getConnectionFromDriver(Properties props) throws SQLException {
        String url = this.getUrl();
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Creating new JDBC DriverManager Connection to [" + url + "]");
        }

        return this.getConnectionFromDriverManager(url, props);
    }

    protected Connection getConnectionFromDriverManager(String url, Properties props) throws SQLException {
        return DriverManager.getConnection(url, props);
    }


}
