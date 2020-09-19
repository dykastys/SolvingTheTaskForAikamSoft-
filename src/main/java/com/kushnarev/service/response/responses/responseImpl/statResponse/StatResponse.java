package com.kushnarev.service.response.responses.responseImpl.statResponse;

import com.kushnarev.service.response.responses.Response;
import com.kushnarev.service.response.responses.responseImpl.statResponse.responseCustomerAndProduct.ResponseCustomer;

import java.util.List;

public class StatResponse implements Response {
    private String type;
    private int totalDays;
    private List<ResponseCustomer> customers;
    private long totalExpenses;
    private double avgExpenses;

    public StatResponse() { }

    public StatResponse(String type, int totalDays, List<ResponseCustomer> customers, long totalExpenses, double avgExpenses) {
        this.type = type;
        this.totalDays = totalDays;
        this.customers = customers;
        this.totalExpenses = totalExpenses;
        this.avgExpenses = avgExpenses;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public List<ResponseCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<ResponseCustomer> customers) {
        this.customers = customers;
    }

    public long getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(long totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getAvgExpenses() {
        return avgExpenses;
    }

    public void setAvgExpenses(double avgExpenses) {
        this.avgExpenses = avgExpenses;
    }
}
