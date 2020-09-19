package com.kushnarev.dao;

import com.kushnarev.entities.Customer;
import com.kushnarev.entities.Product;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface DaoProduct {
    List<Product> getProductsByCustomerByDateRange(Customer customer, Date startDate, Date endDate) throws IOException, SQLException;
}
