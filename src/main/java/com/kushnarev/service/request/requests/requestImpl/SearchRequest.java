package com.kushnarev.service.request.requests.requestImpl;

import com.kushnarev.service.request.criteria.Criteria;
import com.kushnarev.service.request.requests.Request;
import com.kushnarev.service.request.requests.requestData.RequestData;

import java.util.List;

public class SearchRequest implements Request {

    private List<Criteria> criterias;

    public SearchRequest() { }

    @Override
    public RequestData getRequestData() {
        return new RequestData(criterias);
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
    }
}

