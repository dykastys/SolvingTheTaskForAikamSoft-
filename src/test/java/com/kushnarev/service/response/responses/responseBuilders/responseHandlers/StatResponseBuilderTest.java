package com.kushnarev.service.response.responses.responseBuilders.responseHandlers;

import com.kushnarev.dao.DaoCustomer;
import com.kushnarev.dao.DaoProduct;
import com.kushnarev.entities.Customer;
import com.kushnarev.entities.Product;
import com.kushnarev.service.response.responses.responseImpl.statResponse.responseCustomerAndProduct.ResponseCustomer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class StatResponseBuilderTest {

    private DaoCustomer daoCustomer;
    private DaoProduct daoProduct;
    private List<Date> dateList;

    @Before
    public void setUp() {
        this.daoCustomer = mock(DaoCustomer.class);
        this.daoProduct = mock(DaoProduct.class);
        this.dateList = new ArrayList<>();
        this.fillDateList();
    }

    private void fillDateList() {
        Date startDate = new Date(System.currentTimeMillis() - (6*24*60*60*1000));
        Date endDate = new Date(System.currentTimeMillis());
        dateList.add(startDate);
        dateList.add(endDate);
    }

    @Test
    public void test() throws IOException, SQLException {
        Date startDate = dateList.get(0);
        Date endDate = dateList.get(1);

        List<Customer> customers = getCustomerList();
        List<Product> products = getProductList();

        when(daoCustomer.getCustomersByDateOfPurchase(startDate, endDate))
                .thenReturn(customers);
        when(daoProduct.getProductsByCustomerByDateRange(customers.get(0), startDate, endDate))
                .thenReturn(products);

        List<ResponseCustomer> responseCustomers = StatResponseBuilder.getCustomersByDateRange(daoCustomer, daoProduct, dateList);

        assertThat(responseCustomers.size(), is(1));

        verify(daoCustomer).getCustomersByDateOfPurchase(startDate, endDate);
        verify(daoProduct).getProductsByCustomerByDateRange(customers.get(0), startDate, endDate);
        verifyNoMoreInteractions(daoCustomer);
    }

    private List<Customer> getCustomerList() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customers.add(customer);
        return customers;
    }

    private List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(2L);
        product.setProductName("Клюшка");
        product.setProductPrice(12);
        products.add(product);
        return products;
    }
}