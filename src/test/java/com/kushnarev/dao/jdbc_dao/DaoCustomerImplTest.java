package com.kushnarev.dao.jdbc_dao;

import com.kushnarev.dao.jdbc_dao.worker.JdbcWorker;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.kushnarev.dao.jdbc_dao.query_constants.DaoQueryConstants.*;
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
        when(connection.prepareStatement(SELECT_CUSTOMERS_BY_LAST_NAME)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doResultSet(lastName, false);

        daoCustomer.getCustomersByLastName(lastName);

        verify(worker).getConnection();
        verify(connection).prepareStatement(SELECT_CUSTOMERS_BY_LAST_NAME);
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
        when(connection.prepareStatement(SELECT_CUSTOMERS_BY_PRODUCT_NAME_AND_TIMES)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doResultSet(null, false);

        daoCustomer.getCustomersByProductNameAndTimes(productName, times);

        verify(worker).getConnection();
        verify(connection).prepareStatement(SELECT_CUSTOMERS_BY_PRODUCT_NAME_AND_TIMES);
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
        when(connection.prepareStatement(SELECT_CUSTOMERS_BY_MIN_MAX_EXPENSES)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doResultSet(null, false);

        daoCustomer.getCustomersByMinMaxExpenses(min, max);

        verify(worker).getConnection();
        verify(connection).prepareStatement(SELECT_CUSTOMERS_BY_MIN_MAX_EXPENSES);
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
        doResultSet(null, false);

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

    @Test
    public void test_getCustomersByDateOfPurchase() throws ParseException, IOException, SQLException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilStartDate = format.parse("2020-09-14");
        java.util.Date utilEndDate = format.parse("2020-09-26");

        Date startDate = new Date(utilStartDate.getTime());
        Date endDate = new Date(utilEndDate.getTime());

        when(worker.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(SELECT_CUSTOMERS_BY_DATE_OF_PURCHASE)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doResultSet(null, true);

        daoCustomer.getCustomersByDateOfPurchase(startDate, endDate);

        verify(worker).getConnection();
        verify(connection).prepareStatement(SELECT_CUSTOMERS_BY_DATE_OF_PURCHASE);
        verify(preparedStatement).setDate(1, startDate);
        verify(preparedStatement).setDate(2, endDate);
        verify(preparedStatement).executeQuery();
        verify(connection).close();
        verify(preparedStatement).close();
        verify(resultSet).close();
        verifyNoMoreInteractions(worker, connection, preparedStatement);
    }

    private void doResultSet(String lastName, boolean expense) throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("first_name")).thenReturn("Иван");
        if(lastName != null) {
            when(resultSet.getString("last_name")).thenReturn(lastName);
        }else{
            when(resultSet.getString("last_name")).thenReturn("Петров");
        }
        if(expense) {
            when(resultSet.getLong("expense")).thenReturn(505L);
        }
    }
}