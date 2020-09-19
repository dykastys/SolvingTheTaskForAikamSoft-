package com.kushnarev.service.response.responses.responseBuilders.responseHandlers;

import com.kushnarev.dao.DaoCustomer;
import com.kushnarev.dao.jdbc_dao.DaoCustomerImpl;
import com.kushnarev.entities.Customer;
import com.kushnarev.service.request.criteria.Criteria;
import com.kushnarev.service.response.responses.responseImpl.searchResponse.searchResult.SearchResult;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class SearchResponseBuilderTest {

    private DaoCustomer daoCustomer;
    private List<Criteria> criteriaList;

    @Before
    public void setUp() {
        this.daoCustomer = mock(DaoCustomer.class);
        this.criteriaList = new ArrayList<>();
        this.fillCriteriaList();
    }

    private void fillCriteriaList() {
        Criteria criteria1 = new Criteria();
        criteria1.setProductName("Кирпич");
        criteria1.setMinTimes(1);

        Criteria criteria2 = new Criteria();
        criteria2.setLastName("Шарапов");

        criteriaList.add(criteria1);
        criteriaList.add(criteria2);
    }

    @Test
    public void test_getSearchResultsByCriterias() throws IOException, SQLException {
        when(daoCustomer.getCustomersByProductNameAndTimes("Кирпич", 1))
                .thenReturn(getListCustomer());
        when(daoCustomer.getCustomersByLastName("Шарапов")).thenReturn(getListCustomer());

        List<SearchResult> results = SearchResponseBuilder.getSearchResultsByCriterias(daoCustomer, criteriaList);

        assertThat(results.size(), is(2));

        verify(daoCustomer).getCustomersByProductNameAndTimes("Кирпич", 1);
        verify(daoCustomer).getCustomersByLastName("Шарапов");
        verifyNoMoreInteractions(daoCustomer);
    }

    private List<Customer> getListCustomer() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(3L);
        customer.setFirstName("Владимир");
        customer.setLastName("Шарапов");
        customers.add(customer);
        return customers;
    }
}