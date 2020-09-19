package com.kushnarev.service.response.responses.responseImpl.statResponse.responseCustomerAndProduct;

import java.util.Objects;

public class ResponseProduct {
    private String name;
    private long expenses;

    public ResponseProduct() { }

    public ResponseProduct(String name, long expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExpenses() {
        return expenses;
    }

    public void setExpenses(long expenses) {
        this.expenses = expenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseProduct that = (ResponseProduct) o;
        return expenses == that.expenses &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expenses);
    }

    @Override
    public String toString() {
        return "ResponseProduct{" +
                "name='" + name + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}
