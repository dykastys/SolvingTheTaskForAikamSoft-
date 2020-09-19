package com.kushnarev.dao.jdbc_dao.worker;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class JdbcWorkerImplTest {

    @Test
    public void testFileProperties() throws IOException {
        InputStream is = JdbcWorkerImpl.class.getClassLoader().getResourceAsStream("jdbcConnection.properties");
        assertThat(is, notNullValue());
        Properties props = new Properties();
        props.load(is);
        assertThat(props.getProperty("url"), notNullValue());
        assertThat(props.getProperty("userName"), notNullValue());
        assertThat(props.getProperty("password"), notNullValue());
    }

    @Test
    public void testJdbcWorkerIsSingleton() {
        JdbcWorker worker1 = JdbcWorkerImpl.getInstance();
        JdbcWorker worker2 = JdbcWorkerImpl.getInstance();
        assertThat(worker1, is(worker2));
    }

    @Test
    public void test() throws IOException, SQLException {
        JdbcWorker worker = JdbcWorkerImpl.getInstance();
        Connection connection = worker.getConnection();
        assertThat(connection, notNullValue());
    }
}