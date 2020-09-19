package com.kushnarev.service.response.responses.responseBuilders.responseHandlers;

import com.kushnarev.dao.DaoCustomer;
import com.kushnarev.dao.DaoProduct;
import com.kushnarev.entities.Customer;
import com.kushnarev.entities.Product;
import com.kushnarev.service.response.responses.responseImpl.statResponse.responseCustomerAndProduct.ResponseCustomer;
import com.kushnarev.service.response.responses.responseImpl.statResponse.responseCustomerAndProduct.ResponseProduct;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatResponseBuilder {

    public static List<ResponseCustomer> getCustomersByDateRange(DaoCustomer daoCustomer, DaoProduct daoProduct, List<Date> dates) throws IOException, SQLException {
        List<ResponseCustomer> result = new ArrayList<>();

        Date startDate = dates.get(0);
        Date endDate = dates.get(1);

        List<Customer> customers = daoCustomer.getCustomersByDateOfPurchase(startDate, endDate);
        for(Customer customer: customers) {
            ResponseCustomer rCustomer = new ResponseCustomer();
            rCustomer.setName(customer.getLastName() + " " + customer.getFirstName());
            List<ResponseProduct> products = getResponseProducts(daoProduct, customer, startDate, endDate);
            rCustomer.setPurchases(products);
            rCustomer.setTotalExpenses(products.stream().mapToLong(ResponseProduct::getExpenses).sum());
            result.add(rCustomer);
        }
        return result;
    }

    private static List<ResponseProduct> getResponseProducts(DaoProduct daoProduct, Customer customer, Date startDate, Date endDate) throws IOException, SQLException {
        List<ResponseProduct> resultProductList = new ArrayList<>();
        List<Product> products = daoProduct.getProductsByCustomerByDateRange(customer, startDate, endDate);
        for (Product product: products) {
            ResponseProduct responseProduct = new ResponseProduct();
            responseProduct.setName(product.getProductName());
            responseProduct.setExpenses(product.getExpense());
            resultProductList.add(responseProduct);
        }
        return resultProductList;
    }
}
