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
            throw new IllegalArgumentException("incorrect json request - several criteria in json request file!");
        }
        this.type = CriteriaType.LAST_NAME;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        if(type != null) {
            throw new IllegalArgumentException("incorrect json request - several criteria in json request file!");
        }
        this.type = CriteriaType.PRODUCT_NAME;
    }

    public Integer getMinTimes() {
        return minTimes;
    }

    public void setMinTimes(Integer minTimes) {
        if(minTimes < 1) {
            throw new IllegalArgumentException(String.format("The times of purchases must be positive. Your value - '%d'!", minTimes));
        }
        this.minTimes = minTimes;
    }

    public Long getMinExpenses() {
        return minExpenses;
    }

    public void setMinExpenses(Long minExpenses) {
        this.minExpenses = minExpenses;
        if(type != null) {
            throw new IllegalArgumentException("incorrect json request - several criteria in json request file!");
        }
        this.type = CriteriaType.MIN_MAX;
    }

    public Long getMaxExpenses() {
        return maxExpenses;
    }

    public void setMaxExpenses(Long maxExpenses) {
        if(maxExpenses < minExpenses) {
            throw new IllegalArgumentException("the max expenses can't be less than the min expenses!");
        }
        this.maxExpenses = maxExpenses;
    }

    public Integer getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(Integer badCustomers) {
        if(badCustomers < 1) {
            throw new IllegalArgumentException(String.format("quantity of bad customers must be positive. Your value - '%d'!", badCustomers));
        }
        this.badCustomers = badCustomers;
        if(type != null) {
            throw new IllegalArgumentException("incorrect json request - several criteria in json request file!");
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
