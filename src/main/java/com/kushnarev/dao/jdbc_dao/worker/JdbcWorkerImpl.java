package com.kushnarev.dao.jdbc_dao.worker;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcWorkerImpl implements JdbcWorker {

    private static volatile JdbcWorker instance;

    private JdbcWorkerImpl() { }

    public static JdbcWorker getInstance() {
        JdbcWorker localInstance = instance;
        if(localInstance == null) {
            synchronized (JdbcWorkerImpl.class) {
                localInstance = instance;
                if(localInstance == null) {
                    instance = localInstance = new JdbcWorkerImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Connection getConnection() throws IOException, SQLException {
        InputStream is = JdbcWorkerImpl
                .class.getClassLoader()
                .getResourceAsStream("jdbcConnection.properties");

        Properties props = new Properties();
        props.load(is);

        return DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("userName"),
                props.getProperty("password")
        );
    }
}
