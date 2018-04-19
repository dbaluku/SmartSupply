package org.smartsupply.api.service.impl;

import org.apache.commons.lang.StringUtils;
import org.smartsupply.api.security.Security;
import org.smartsupply.model.util.MyStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionService {

    private static Logger logStatic = LoggerFactory.getLogger(ConnectionService.class);

//    private static HikariConnPool connPool = null;
//
//    public static Connection getConnection() throws Exception {
//        if (connPool == null) {
//            connPool = createConnPool();
//        }
//        return connPool.getConnection();
//    }
//
//    private static void memoryStats(){
//        Runtime runtime = Runtime.getRuntime();
//        System.out.println("max  - "+runtime.maxMemory());
//        System.out.println("tot  - "+runtime.totalMemory());
//        System.out.println("free - "+runtime.freeMemory());
//    }

    //    private static Connection connection;
//
//    public static void setConnection(Connection conn) {
//        connection = conn;
//    }

    public ConnectionService(DataSource dataSource){
        ConnectionService.dataSource = dataSource;
    }
    private static DataSource dataSource;

    public static Connection getConnection() throws Exception {
        Connection conn = dataSource.getConnection();
        return conn;
    }

//    private static Connection connection;
//
//    public static void setConnection(Connection conn) {
//        connection = conn;
//    }

    private static HikariConnPool createConnPool() throws Exception {
        Properties prop = new Properties();
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("RMS_SETTINGS.properties");
        if (stream != null) {
            prop.load(stream);
        }

        String forname = prop.getProperty("hibernate.connection.driver_class");
        String dataSourseClassName = prop.getProperty("data_source_class_name");
        String databaseurl = prop.getProperty("hibernate.connection.url");
        String username = prop.getProperty("hibernate.connection.username");
        String password = prop.getProperty("hibernate.connection.password");

        return new HikariConnPool(forname, dataSourseClassName, databaseurl, username, Security.decrypt(password));
    }

    public static Connection getOldConnection() throws Exception {
        Properties prop = new Properties();

        try {
            // load a properties file
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("GENERAL.properties");
            if (stream != null) {
                prop.load(stream);
            }

            String forname = prop.getProperty("old" + ".forname");
            String databaseurl = prop.getProperty("old" + ".database");
            String username = prop.getProperty("old" + ".username");
            String password = prop.getProperty("old" + ".password");

            Class.forName(forname);
            Connection con = DriverManager.getConnection(databaseurl, username, password);

            return con;

        } catch (Exception ex) {
            logStatic.error("Error connecting to DB", ex);
            throw ex;
        }
    }

    public static Connection getNewConnection() throws Exception {
        Properties prop = new Properties();

        try {
            // load a properties file
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("GENERAL.properties");
            if (stream != null) {
                prop.load(stream);
            }

            String forname = prop.getProperty("newdb" + ".forname");
            String databaseurl = prop.getProperty("newdb" + ".database");
            String username = prop.getProperty("newdb" + ".username");
            String password = prop.getProperty("newdb" + ".password");

            Class.forName(forname);
            Connection con = DriverManager.getConnection(databaseurl, username, password);

            return con;

        } catch (Exception ex) {
            logStatic.error("Error connecting to DB", ex);
            throw ex;
        }
    }

    public static void close(Connection conn, Statement s, ResultSet rs) throws Exception {
        if (rs != null)
            rs.close();
        close(conn, s);
    }

    public static void close(Connection conn, Statement s) throws Exception {
        if (s != null)
            s.close();
        if (conn != null)
            conn.close();
    }

    public static String generateValuesString(int numRecords, int numFields) {

        String fields = "";

        for (int i = 0; i < numFields; i++)
            fields += StringUtils.isBlank(fields) ? "?" : ", ?";

        fields = MyStringUtil.bracketed(fields);

        String x = "";
        for (int i = 0; i < numRecords; i++)
            x += StringUtils.isBlank(x) ? fields : "," + fields;

        x += ";";
        return x;
    }
}