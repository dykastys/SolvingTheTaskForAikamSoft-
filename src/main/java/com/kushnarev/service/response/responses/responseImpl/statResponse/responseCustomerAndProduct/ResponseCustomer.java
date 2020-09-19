package com.kushnarev.service.response.responses.responseImpl.statResponse.responseCustomerAndProduct;

import java.util.List;
import java.util.Objects;

public class ResponseCustomer {
    private String name;
    private List<ResponseProduct> purchases;
    private long totalExpenses;

    public ResponseCustomer() { }

    public ResponseCustomer(String name, List<ResponseProduct> purchases, long totalExpenses) {
        this.name = name;
        this.purchases = purchases;
        this.totalExpenses = totalExpenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResponseProduct> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<ResponseProduct> purchases) {
        this.purchases = purchases;
    }

    public long getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(long totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseCustomer that = (ResponseCustomer) o;
        return totalExpenses == that.totalExpenses &&
                Objects.equals(name, that.name) &&
                Objects.equals(purchases, that.purchases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, purchases, totalExpenses);
    }

    @Override
    public String toString() {
        return "ResponseCustomer{" +
                "name='" + name + '\'' +
                ", purchases=" + purchases +
                ", totalExpenses=" + totalExpenses +
                '}';
    }
}
