package com.kushnarev.service.response.responses.responseBuilders;

import com.kushnarev.dao.DaoCustomer;
import com.kushnarev.dao.DaoProduct;
import com.kushnarev.entities.Customer;
import com.kushnarev.service.request.criteria.Criteria;
import com.kushnarev.service.request.requests.Request;
import com.kushnarev.service.request.requests.requestImpl.SearchRequest;
import com.kushnarev.service.request.requests.requestImpl.StatRequest;
import com.kushnarev.service.response.responses.Response;
import com.kushnarev.service.response.responses.responseImpl.searchResponse.SearchResponse;
import com.kushnarev.service.response.responses.responseImpl.searchResponse.searchResult.SearchResult;
import com.kushnarev.service.response.responses.responseImpl.statResponse.StatResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResponseBuilderTest {

    private DaoCustomer daoCustomer;
    private DaoProduct daoProduct;
    private Request request;

    private Date startDate;
    private Date endDate;

    @Before
    public void setUp() {
        this.daoCustomer = mock(DaoCustomer.class);
        this.daoProduct = mock(DaoProduct.class);
        this.startDate = new Date(System.currentTimeMillis() - (6*24*60*60*1000));
        this.endDate = new Date(System.currentTimeMillis());
    }

    @Test
    public void test_formResponse_SearchType() throws IOException, SQLException {
        this.request = getSearchRequest();
        Response response = ResponseBuilder.formResponse(daoCustomer, daoProduct, request);
        assertThat(response, instanceOf(SearchResponse.class));
        if(response instanceof SearchResponse) {
            assertThat(((SearchResponse) response).getType(), is("search"));
            assertThat(((SearchResponse) response).getResults().size(), is(1));
        }else{
            throw new IllegalArgumentException();
        }
    }

    private Request getSearchRequest() {
        SearchRequest request = new SearchRequest();
        request.setCriterias(getCriteriaList());
        return request;
    }

    private List<Criteria> getCriteriaList() {
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(getCriteria());
        return criteriaList;
    }

    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.setLastName("Петров");
        return criteria;
    }

    @Test
    public void test_formResponse_StatType() throws IOException, SQLException {
        this.request = getStatRequest();
        when(daoCustomer.getCustomersByDateOfPurchase(startDate, endDate))
                .thenReturn(getCustomerList());

        Response response = ResponseBuilder.formResponse(daoCustomer, daoProduct, request);

        assertThat(response, instanceOf(StatResponse.class));
        if(response instanceof StatResponse) {
            assertThat(((StatResponse) response).getType(), is("stat"));
            assertThat(((StatResponse) response).getCustomers().size(), is(1));
        }else{
            throw new IllegalArgumentException();
        }
    }

    private Request getStatRequest() {
        StatRequest request = new StatRequest();
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        return request;
    }

    private List<Customer> getCustomerList() {
        List<Customer> customers = new ArrayList<>();
        customers.add(getCustomer());
        return customers;
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Олег");
        customer.setLastName("Невин");
        return customer;
    }
}