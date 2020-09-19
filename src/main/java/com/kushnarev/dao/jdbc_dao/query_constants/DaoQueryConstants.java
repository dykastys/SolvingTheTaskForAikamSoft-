package com.kushnarev.dao.jdbc_dao.query_constants;

public class DaoQueryConstants {

    public static final String SELECT_CUSTOMERS_BY_LAST_NAME =
            "select * from customers where last_name=?";

    public static final String SELECT_CUSTOMERS_BY_PRODUCT_NAME_AND_TIMES =
            "select c.id, c.first_name, c.last_name from customers c " +
                    "left join purchases p on c.id = p.customer_id " +
                    "left join products pr on p.product_id = pr.id " +
                    "where product_name = ? group by c.id having count(p.product_id) >= ?";

    public static final String SELECT_CUSTOMERS_BY_MIN_MAX_EXPENSES =
            "select c.id, c.first_name, c.last_name from customers c " +
                    "left join purchases p on c.id = p.customer_id " +
                    "left join products p2 on p.product_id = p2.id " +
                    "group by c.id having sum(p2.product_price) >= ? and sum(p2.product_price) <= ?";

    public static final String SELECT_BED_CUSTOMERS =
            "select c.id, c.first_name, c.last_name from customers c " +
                    "left join purchases p on c.id = p.customer_id " +
                    "left join products pr on p.product_id = pr.id " +
                    "group by c.id order by sum(pr.product_price) limit ?";

    public static final String SELECT_CUSTOMERS_BY_DATE_OF_PURCHASE =
            "select c.id, c.first_name, c.last_name, sum(pr.product_price) expense from customers c " +
                    "left join purchases p on c.id = p.customer_id " +
                    "left join products pr on p.product_id = pr.id " +
                    "where date>=? and date<=? " +
                    "group by c.id order by sum(pr.product_price) desc";

    public static final String SELECT_PRODUCTS_BY_CUSTOMER_AND_DATE_RANGE =
            "select pr.id, product_name, product_price, sum(product_price) expense from products pr " +
                    "left join purchases p on pr.id = p.product_id " +
                    "left join customers c on p.customer_id = c.id " +
                    "where c.id=1 and (date>='2020-09-14' and date<='2020-09-26') " +
                    "group by pr.id order by sum(product_price) desc";
}
