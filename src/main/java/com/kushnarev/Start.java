package com.kushnarev;

import com.kushnarev.dao.DaoCustomer;
import com.kushnarev.dao.jdbc_dao.DaoCustomerImpl;
import com.kushnarev.dao.jdbc_dao.worker.JdbcWorker;
import com.kushnarev.dao.jdbc_dao.worker.JdbcWorkerImpl;
import com.kushnarev.entities.Customer;
import com.kushnarev.service.request.requestHandler.RequestHandler;
import com.kushnarev.service.request.requestHandler.RequestHandlerImpl;
import com.kushnarev.service.request.requests.Request;
import com.kushnarev.service.response.ResponseHandler;
import com.kushnarev.service.response.responses.Response;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Start {
    public static void main(String[] args) throws IOException, SQLException {
        Date date = new Date(System.currentTimeMillis() - (24*60*60*1000*6));

        System.out.println(date);

        DaoCustomer daoCustomer = new DaoCustomerImpl(JdbcWorkerImpl.getInstance());
        List<Customer> customers = daoCustomer
                .getCustomersByDateOfPurchase(
                        date,
                        new Date(System.currentTimeMillis()));

        for(Customer customer: customers) {
            System.out.println(customer);
            System.out.println(customer.getSumOfPurchase());
        }



        /*RequestHandler handler = new RequestHandlerImpl(args[1]);

        Request request = null;

        switch (args[0]) {
            case "search":
                request = handler.getSearchRequestFromJsonFile();
                break;
            case "stat":
                request = handler.getStatRequestFromJsonFile();
        }

        ResponseHandler responseHandler = new ResponseHandler(request);
        Response response = responseHandler.formResponse();*/
    }
}
