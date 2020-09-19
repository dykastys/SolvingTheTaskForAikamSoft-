package com.kushnarev.service.response.responses.responseBuilders.responseHandlers;

import com.kushnarev.dao.DaoCustomer;
import com.kushnarev.service.request.criteria.Criteria;
import com.kushnarev.service.response.responses.responseImpl.searchResponse.searchResult.SearchResult;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchResponseBuilder {

    public static List<SearchResult> getSearchResultsByCriterias(DaoCustomer daoCustomer, List<Criteria> criterias) throws IOException, SQLException {
        List<SearchResult> results = new ArrayList<>();
        for(Criteria criteria: criterias) {
            SearchResult result = new SearchResult();
            switch (criteria.getType()) {
                case LAST_NAME:
                    addResultByLastName(result, criteria, daoCustomer, results); break;
                case PRODUCT_NAME:
                    addResultByProductName(result, criteria, daoCustomer, results); break;
                case MIN_MAX:
                    addResultByMinMax(result, criteria, daoCustomer, results); break;
                case BAD_CUSTOMERS:
                    addResultByBadCustomers(result, criteria, daoCustomer, results); break;
                default:
                    throw new IllegalArgumentException("Incorrect criteria type");
            }
        }
        return results;
    }

    private static void addResultByLastName(SearchResult result, Criteria criteria, DaoCustomer daoCustomer, List<SearchResult> results) throws IOException, SQLException {
        result.setCriteria(criteria);
        result.setResults(daoCustomer.getCustomersByLastName(criteria.getLastName()));
        results.add(result);
    }

    private static void addResultByProductName(SearchResult result, Criteria criteria, DaoCustomer daoCustomer, List<SearchResult> results) throws IOException, SQLException {
        result.setCriteria(criteria);
        result.setResults(daoCustomer
                .getCustomersByProductNameAndTimes(
                        criteria.getProductName(), criteria.getMinTimes()));
        results.add(result);
    }

    private static void addResultByMinMax(SearchResult result, Criteria criteria, DaoCustomer daoCustomer, List<SearchResult> results) throws IOException, SQLException {
        result.setCriteria(criteria);
        result.setResults(daoCustomer
                .getCustomersByMinMaxExpenses(
                        criteria.getMinExpenses(), criteria.getMaxExpenses()));
        results.add(result);
    }

    private static void addResultByBadCustomers(SearchResult result, Criteria criteria, DaoCustomer daoCustomer, List<SearchResult> results) throws IOException, SQLException {
        result.setCriteria(criteria);
        result.setResults(daoCustomer.getBadCustomers(criteria.getBadCustomers()));
        results.add(result);
    }
}
