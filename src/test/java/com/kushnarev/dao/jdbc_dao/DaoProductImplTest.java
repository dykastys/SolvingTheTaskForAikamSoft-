package com.kushnarev.dao.jdbc_dao;

import com.kushnarev.dao.DaoProduct;
import com.kushnarev.dao.jdbc_dao.worker.JdbcWorker;
import com.kushnarev.entities.Customer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.kushnarev.dao.jdbc_dao.query_constants.DaoQueryConstants.*;
import static org.mockito.Mockito.*;

public class DaoProductImplTest {

    private DaoProduct daoProduct;

    private JdbcWorker worker;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Before
    public void setUp() {
        this.worker = mock(JdbcWorker.class);
        this.daoProduct = new DaoProductImpl(worker);
        this.connection = mock(Connection.class);
        this.preparedStatement = mock(PreparedStatement.class);
        this.resultSet = mock(ResultSet.class);
    }

    @Test
    public void test_getProductsByCustomerByDateRange() throws IOException, SQLException, ParseException {
        Customer customer = new Customer("Иван", "Иванов");
        customer.setId(3L);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilStartDate = format.parse("2020-09-14");
        java.util.Date utilEndDate = format.parse("2020-09-26");

        Date startDate = new Date(utilStartDate.getTime());
        Date endDate = new Date(utilEndDate.getTime());

        when(worker.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(SELECT_PRODUCTS_BY_CUSTOMER_AND_DATE_RANGE)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doResultSet();

        daoProduct.getProductsByCustomerByDateRange(customer, startDate, endDate);

        verifyMocks(customer, startDate, endDate);
    }

    private void doResultSet() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id")).thenReturn(3L);
        when(resultSet.getString("product_name")).thenReturn("Хлеб");
        when(resultSet.getLong("product_price")).thenReturn(23L);
        when(resultSet.getLong("expense")).thenReturn(300L);
    }

    private void verifyMocks(Customer customer, Date startDate, Date endDate) throws IOException, SQLException {
        verify(worker).getConnection();
        verify(connection).prepareStatement(SELECT_PRODUCTS_BY_CUSTOMER_AND_DATE_RANGE);
        verify(preparedStatement).setLong(1, customer.getId());
        verify(preparedStatement).setDate(2, startDate);
        verify(preparedStatement).setDate(3, endDate);
        verify(preparedStatement).executeQuery();
        verify(connection).close();
        verify(preparedStatement).close();
        verify(resultSet).close();
        verifyNoMoreInteractions(worker, connection, preparedStatement);
    }
}