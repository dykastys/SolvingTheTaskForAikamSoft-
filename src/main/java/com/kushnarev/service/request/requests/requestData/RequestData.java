package com.kushnarev.service.request.requests.requestData;

import com.kushnarev.service.request.criteria.Criteria;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RequestData {

    private List<Criteria> criterias;
    private List<Date> dates;

    public RequestData(List<Criteria> criterias) {
        this.criterias = criterias;
    }

    public RequestData(Date startDate, Date endDate) {
        dates = new ArrayList<>();
        dates.add(startDate);
        dates.add(endDate);
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public List<Date> getDates() {
        return dates;
    }
}
