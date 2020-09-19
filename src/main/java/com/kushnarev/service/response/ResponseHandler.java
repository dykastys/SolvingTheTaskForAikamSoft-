package com.kushnarev.service.response;

import com.kushnarev.dao.DaoCustomer;
import com.kushnarev.dao.jdbc_dao.DaoCustomerImpl;
import com.kushnarev.dao.jdbc_dao.worker.JdbcWorker;
import com.kushnarev.dao.jdbc_dao.worker.JdbcWorkerImpl;
import com.kushnarev.service.request.criteria.Criteria;
import com.kushnarev.service.request.requests.Request;
import com.kushnarev.service.request.requests.requestImpl.SearchRequest;
import com.kushnarev.service.request.requests.requestImpl.StatRequest;
import com.kushnarev.service.response.responses.Response;
import com.kushnarev.service.response.responses.responseImpl.searchResponse.Result;
import com.kushnarev.service.response.responses.responseImpl.searchResponse.SearchResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponseHandler {

    private Request request;

    public ResponseHandler() { }

    public ResponseHandler(Request request) {
        this.request = request;
    }

    public Response formResponse() throws IOException, SQLException {
        if(request instanceof SearchRequest) {
            List<Result> results = new ArrayList<>();

            JdbcWorker jdbcWorker = JdbcWorkerImpl.getInstance();
            DaoCustomer daoCustomer = new DaoCustomerImpl(jdbcWorker);

            List<Criteria> criterias = request.getRequestData().getCriterias();
            for(Criteria criteria: criterias) {

                Result result = new Result();

                switch (criteria.getType()) {
                    case LAST_NAME:
                        result.setCriteria(criteria);
                        result.setResults(daoCustomer.getCustomersByLastName(criteria.getLastName()));
                        results.add(result);
                        break;
                    case PRODUCT_NAME:
                        result.setCriteria(criteria);
                        result.setResults(daoCustomer
                                .getCustomersByProductNameAndTimes(
                                        criteria.getProductName(), criteria.getMinTimes()));
                        results.add(result);
                        break;
                    case MIN_MAX:
                        result.setCriteria(criteria);
                        result.setResults(daoCustomer
                                .getCustomersByMinMaxExpenses(
                                        criteria.getMinExpenses(), criteria.getMaxExpenses()));
                        results.add(result);
                        break;
                    case BAD_CUSTOMERS:
                        result.setCriteria(criteria);
                        result.setResults(daoCustomer.getBadCustomers(criteria.getBadCustomers()));
                        results.add(result);
                        break;
                    default:
                        System.out.println("Not right criteria type");
                }
            }

            SearchResponse response = new SearchResponse("search", results);

            return response;
        }
        if(request instanceof StatRequest) {
            System.out.println("stat");
            return null;
        }
        return null;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
