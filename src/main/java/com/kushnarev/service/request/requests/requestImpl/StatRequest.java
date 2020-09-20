package com.kushnarev.service.request.requests.requestImpl;

import com.kushnarev.service.request.requests.Request;
import com.kushnarev.service.request.requests.requestData.RequestData;

import java.sql.Date;

public class StatRequest implements Request {

    private Date startDate;
    private Date endDate;

    public StatRequest() { }

    @Override
    public RequestData getRequestData() {
        if(startDate.getTime() > endDate.getTime()) {
            throw new IllegalArgumentException("end Date cannot be less than start Date in file 'stat json query'");
        }
        return new RequestData(startDate, endDate);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
