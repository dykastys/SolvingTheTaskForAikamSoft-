package com.kushnarev.dao.jdbc_dao.query_constants;

public class DaoCustomerQueryConstants {

    public static final String SELECT_BY_LAST_NAME = "select * from customers where last_name=?";

    public static final String SELECT_BY_PRODUCT_NAME_AND_TIMES =
            "select c.id, c.first_name, c.last_name from customers c " +
                    "left join purchases p on c.id = p.customer_id " +
                    "left join products pr on p.product_id = pr.id " +
                    "where product_name = ? group by c.id having count(p.product_id) >= ?";

    public static final String SELECT_BY_MIN_MAX_EXPENSES =
            "select c.id, c.first_name, c.last_name from customers c " +
                    "left join purchases p on c.id = p.customer_id " +
                    "left join products p2 on p.product_id = p2.id " +
                    "group by c.id " +
                    "having sum(p2.product_price) >= ? and sum(p2.product_price) <= ?";

    public static final String SELECT_BED_CUSTOMERS =
            "select c.id, c.first_name, c.last_name from customers c " +
                    "left join purchases p on c.id = p.customer_id " +
                    "left join products pr on p.product_id = pr.id " +
                    "group by c.id " +
                    "order by sum(pr.product_price) " +
                    "limit ?";
}
