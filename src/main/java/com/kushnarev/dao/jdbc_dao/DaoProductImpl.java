package com.kushnarev.dao.jdbc_dao;

import com.kushnarev.dao.DaoProduct;
import com.kushnarev.dao.jdbc_dao.worker.JdbcWorker;
import com.kushnarev.entities.Customer;
import com.kushnarev.entities.Product;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.kushnarev.dao.jdbc_dao.query_constants.DaoQueryConstants.*;

public class DaoProductImpl implements DaoProduct {

    JdbcWorker worker;

    public DaoProductImpl(JdbcWorker worker) {
        this.worker = worker;
    }

    @Override
    public List<Product> getProductsByCustomerByDateRange(Customer customer, Date startDate, Date endDate) throws IOException, SQLException {
        try(Connection connection = worker.getConnection();
            PreparedStatement statement = connection
                    .prepareStatement(SELECT_PRODUCTS_BY_CUSTOMER_AND_DATE_RANGE)) {
            statement.setLong(1, customer.getId());
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
            ResultSet resultSet = statement.executeQuery();
            return getProductsFromResultSet(resultSet);
        }
    }

    private List<Product> getProductsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setProductPrice(resultSet.getLong("product_price"));
            product.setExpense(resultSet.getLong("expense"));
            products.add(product);
        }
        resultSet.close();
        return products;
    }

    public JdbcWorker getWorker() {
        return worker;
    }

    public void setWorker(JdbcWorker worker) {
        this.worker = worker;
    }
}
