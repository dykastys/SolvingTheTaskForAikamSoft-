package com.kushnarev.dao;

import com.kushnarev.entities.Customer;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface DaoCustomer {
    List<Customer> getCustomersByLastName(String lastName) throws IOException, SQLException;
    List<Customer> getCustomersByProductNameAndTimes(String productName, int times) throws IOException, SQLException;
    List<Customer> getCustomersByMinMaxExpenses(long minExpenses, long maxExpenses) throws IOException, SQLException;
    List<Customer> getBadCustomers(int count) throws IOException, SQLException;

    List<Customer> getCustomersByDateOfPurchase(Date startDate, Date endDate) throws IOException, SQLException;
}
