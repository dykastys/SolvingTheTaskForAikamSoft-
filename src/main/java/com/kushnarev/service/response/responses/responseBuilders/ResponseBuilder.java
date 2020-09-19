package com.kushnarev.service.response.responses.responseBuilders;

import com.kushnarev.dao.DaoCustomer;
import com.kushnarev.dao.DaoProduct;
import com.kushnarev.dao.jdbc_dao.DaoCustomerImpl;
import com.kushnarev.dao.jdbc_dao.DaoProductImpl;
import com.kushnarev.dao.jdbc_dao.worker.JdbcWorker;
import com.kushnarev.dao.jdbc_dao.worker.JdbcWorkerImpl;
import com.kushnarev.service.request.criteria.Criteria;
import com.kushnarev.service.request.requests.Request;
import com.kushnarev.service.request.requests.requestImpl.SearchRequest;
import com.kushnarev.service.request.requests.requestImpl.StatRequest;
import com.kushnarev.service.response.responses.Response;
import com.kushnarev.service.response.responses.responseBuilders.responseHandlers.SearchResponseBuilder;
import com.kushnarev.service.response.responses.responseBuilders.responseHandlers.StatResponseBuilder;
import com.kushnarev.service.response.responses.responseImpl.searchResponse.searchResult.SearchResult;
import com.kushnarev.service.response.responses.responseImpl.searchResponse.SearchResponse;
import com.kushnarev.service.response.responses.responseImpl.statResponse.responseCustomerAndProduct.ResponseCustomer;
import com.kushnarev.service.response.responses.responseImpl.statResponse.StatResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ResponseBuilder {

    public static Response formResponse(DaoCustomer daoCustomer, DaoProduct daoProduct, Request request) throws IOException, SQLException {

        if(request instanceof SearchRequest) {
            List<Criteria> criterias = request.getRequestData().getCriterias();
            List<SearchResult> results = SearchResponseBuilder.getSearchResultsByCriterias(daoCustomer, criterias);
            return new SearchResponse("search", results);
        }

        if(request instanceof StatRequest) {
            List<Date> dates = request.getRequestData().getDates();
            int days = (int) ((dates.get(1).getTime() - dates.get(0).getTime())/1000/60/60/24) + 1;
            List<ResponseCustomer> customers = StatResponseBuilder.getCustomersByDateRange(daoCustomer, daoProduct, dates);
            long totalExpense = customers.stream().mapToLong(ResponseCustomer::getTotalExpenses).sum();
            double avgExpense = (totalExpense*1.0) / customers.size();
            return new StatResponse("stat", days, customers, totalExpense, avgExpense);
        }

        throw new IllegalArgumentException("unknown request type");
    }
}
