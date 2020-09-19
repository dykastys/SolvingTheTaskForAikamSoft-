package com.kushnarev.dao.jdbc_dao;

import com.kushnarev.dao.DaoCustomer;
import com.kushnarev.dao.jdbc_dao.worker.JdbcWorker;
import com.kushnarev.entities.Customer;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.kushnarev.dao.jdbc_dao.query_constants.DaoQueryConstants.*;

public class DaoCustomerImpl implements DaoCustomer {

    JdbcWorker worker;

    public DaoCustomerImpl(JdbcWorker worker) {
        this.worker = worker;
    }

    @Override
    public List<Customer> getCustomersByLastName(String lastName) throws IOException, SQLException {
        try(Connection connection = worker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMERS_BY_LAST_NAME)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            return getCustomersFromResultSet(resultSet,false);
        }
    }

    @Override
    public List<Customer> getCustomersByProductNameAndTimes(String productName, int times) throws IOException, SQLException {
        try(Connection connection = worker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMERS_BY_PRODUCT_NAME_AND_TIMES)) {
            statement.setString(1, productName);
            statement.setInt(2, times);
            ResultSet resultSet = statement.executeQuery();
            return getCustomersFromResultSet(resultSet,false);
        }
    }

    @Override
    public List<Customer> getCustomersByMinMaxExpenses(long minExpenses, long maxExpenses) throws IOException, SQLException {
        try(Connection connection = worker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMERS_BY_MIN_MAX_EXPENSES)) {
            statement.setLong(1, minExpenses);
            statement.setLong(2, maxExpenses);
            ResultSet resultSet = statement.executeQuery();
            return getCustomersFromResultSet(resultSet,false);
        }
    }

    @Override
    public List<Customer> getBadCustomers(int count) throws IOException, SQLException {
        try(Connection connection = worker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BED_CUSTOMERS)) {
            statement.setInt(1, count);
            ResultSet resultSet = statement.executeQuery();
            return getCustomersFromResultSet(resultSet,false);
        }
    }

    @Override
    public List<Customer> getCustomersByDateOfPurchase(Date startDate, Date endDate) throws IOException, SQLException {
        try(Connection connection = worker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMERS_BY_DATE_OF_PURCHASE)) {
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
            ResultSet resultSet = statement.executeQuery();
            return getCustomersFromResultSet(resultSet,true);
        }
    }

    List<Customer> getCustomersFromResultSet(ResultSet resultSet, boolean sumOfPurchase) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getLong("id"));
            customer.setFirstName(resultSet.getString("first_name"));
            customer.setLastName(resultSet.getString("last_name"));
            if(sumOfPurchase) {
                customer.setSumOfPurchase(resultSet.getLong("expense"));
            }
            customers.add(customer);
        }
        resultSet.close();
        return customers;
    }

    public JdbcWorker getWorker() {
        return worker;
    }

    public void setWorker(JdbcWorker worker) {
        this.worker = worker;
    }
}
