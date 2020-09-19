package com.kushnarev.service.request.criteria;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Criteria {

    @JsonIgnore
    private CriteriaType type;

    private String lastName;
    private String productName;
    private Integer minTimes;
    private Long minExpenses;
    private Long maxExpenses;
    private Integer badCustomers;

    public CriteriaType getType() {
        return type;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        if(type != null) {
            throw new IllegalArgumentException("json request not right");
        }
        this.type = CriteriaType.LAST_NAME;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        if(type != null) {
            throw new IllegalArgumentException("json request not right");
        }
        this.type = CriteriaType.PRODUCT_NAME;
    }

    public Integer getMinTimes() {
        return minTimes;
    }

    public void setMinTimes(Integer minTimes) {
        this.minTimes = minTimes;
    }

    public Long getMinExpenses() {
        return minExpenses;
    }

    public void setMinExpenses(Long minExpenses) {
        this.minExpenses = minExpenses;
        if(type != null) {
            throw new IllegalArgumentException("json request not right");
        }
        this.type = CriteriaType.MIN_MAX;
    }

    public Long getMaxExpenses() {
        return maxExpenses;
    }

    public void setMaxExpenses(Long maxExpenses) {
        this.maxExpenses = maxExpenses;
    }

    public Integer getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(Integer badCustomers) {
        this.badCustomers = badCustomers;
        if(type != null) {
            throw new IllegalArgumentException("json request not right");
        }
        this.type = CriteriaType.BAD_CUSTOMERS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criteria criteria = (Criteria) o;
        return type == criteria.type &&
                Objects.equals(lastName, criteria.lastName) &&
                Objects.equals(productName, criteria.productName) &&
                Objects.equals(minTimes, criteria.minTimes) &&
                Objects.equals(minExpenses, criteria.minExpenses) &&
                Objects.equals(maxExpenses, criteria.maxExpenses) &&
                Objects.equals(badCustomers, criteria.badCustomers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, lastName, productName, minTimes, minExpenses, maxExpenses, badCustomers);
    }
}
