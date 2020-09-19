package com.kushnarev.service.response.responses.responseImpl.searchResponse;

import com.kushnarev.entities.Customer;
import com.kushnarev.service.request.criteria.Criteria;

import java.util.List;

public class Result {
    private Criteria criteria;
    private List<Customer> results;

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public List<Customer> getResults() {
        return results;
    }

    public void setResults(List<Customer> results) {
        this.results = results;
    }
}
