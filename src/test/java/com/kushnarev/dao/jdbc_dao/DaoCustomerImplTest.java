package com.kushnarev.dao.jdbc_dao;

import com.kushnarev.dao.jdbc_dao.worker.JdbcWorker;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.kushnarev.dao.jdbc_dao.query_constants.DaoCustomerQueryConstants.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class DaoCustomerImplTest {

    private DaoCustomerImpl daoCustomer;

    private JdbcWorker worker;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Before
    public void setUp() {
        this.worker = mock(JdbcWorker.class);
        this.daoCustomer = new DaoCustomerImpl(worker);
        this.connection = mock(Connection.class);
        this.preparedStatement = mock(PreparedStatement.class);
        this.resultSet = mock(ResultSet.class);
    }

    @Test
    public void test_getCustomersByLastName() throws IOException, SQLException {
        String lastName = "Петров";

        when(worker.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(SELECT_BY_LAST_NAME)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doResultSet(lastName);

        daoCustomer.getCustomersByLastName(lastName);

        verify(worker).getConnection();
        verify(connection).prepareStatement(SELECT_BY_LAST_NAME);
        verify(preparedStatement).setString(1, lastName);
        verify(preparedStatement).executeQuery();
        verify(connection).close();
        verify(preparedStatement).close();
        verify(resultSet).close();
        verifyNoMoreInteractions(worker, connection, preparedStatement);
    }

    @Test
    public void test_getCustomersByProductNameAndTimes() throws IOException, SQLException {
        String productName = "Минеральная вода";
        int times = 5;

        when(worker.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(SELECT_BY_PRODUCT_NAME_AND_TIMES)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doResultSet(null);

        daoCustomer.getCustomersByProductNameAndTimes(productName, times);

        verify(worker).getConnection();
        verify(connection).prepareStatement(SELECT_BY_PRODUCT_NAME_AND_TIMES);
        verify(preparedStatement).setString(1, productName);
        verify(preparedStatement).setInt(2, times);
        verify(preparedStatement).executeQuery();
        verify(connection).close();
        verify(preparedStatement).close();
        verify(resultSet).close();
        verifyNoMoreInteractions(worker, connection, preparedStatement);
    }

    @Test
    public void test_getCustomersByMinMaxExpenses() throws IOException, SQLException {
        long min = 10;
        long max = 1003;

        when(worker.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(SELECT_BY_MIN_MAX_EXPENSES)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doResultSet(null);

        daoCustomer.getCustomersByMinMaxExpenses(min, max);

        verify(worker).getConnection();
        verify(connection).prepareStatement(SELECT_BY_MIN_MAX_EXPENSES);
        verify(preparedStatement).setLong(1, min);
        verify(preparedStatement).setLong(2, max);
        verify(preparedStatement).executeQuery();
        verify(connection).close();
        verify(preparedStatement).close();
        verify(resultSet).close();
        verifyNoMoreInteractions(worker, connection, preparedStatement);
    }

    @Test
    public void test_getBadCustomers() throws IOException, SQLException {
        int count = 3;

        when(worker.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(SELECT_BED_CUSTOMERS)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doResultSet(null);

        daoCustomer.getBadCustomers(count);

        verify(worker).getConnection();
        verify(connection).prepareStatement(SELECT_BED_CUSTOMERS);
        verify(preparedStatement).setInt(1, count);
        verify(preparedStatement).executeQuery();
        verify(connection).close();
        verify(preparedStatement).close();
        verify(resultSet).close();
        verifyNoMoreInteractions(worker, connection, preparedStatement);
    }

    private void doResultSet(String lastName) throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("first_name")).thenReturn("Иван");
        if(lastName != null) {
            when(resultSet.getString("last_name")).thenReturn(lastName);
        }else{
            when(resultSet.getString("last_name")).thenReturn("Петров");
        }
    }
}