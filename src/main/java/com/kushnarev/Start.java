package com.kushnarev;

import com.kushnarev.dao.DaoCustomer;
import com.kushnarev.dao.DaoProduct;
import com.kushnarev.dao.jdbc_dao.DaoCustomerImpl;
import com.kushnarev.dao.jdbc_dao.DaoProductImpl;
import com.kushnarev.dao.jdbc_dao.worker.JdbcWorker;
import com.kushnarev.dao.jdbc_dao.worker.JdbcWorkerImpl;
import com.kushnarev.service.json.JsonHandler;
import com.kushnarev.service.request.requestHandler.RequestHandler;
import com.kushnarev.service.request.requestHandler.RequestHandlerImpl;
import com.kushnarev.service.request.requests.Request;
import com.kushnarev.service.response.responses.Response;
import com.kushnarev.service.response.responses.responseBuilders.ResponseBuilder;

import java.io.IOException;
import java.sql.SQLException;

public class Start {
    public static void main(String[] args) {
        try {
            JdbcWorker jdbcWorker = JdbcWorkerImpl.getInstance();
            DaoCustomer daoCustomer = new DaoCustomerImpl(jdbcWorker);
            DaoProduct daoProduct = new DaoProductImpl(jdbcWorker);

            RequestHandler handler = new RequestHandlerImpl(args[1]);

            Request request = null;

            switch (args[0]) {
                case "search":
                    request = handler.getSearchRequestFromJsonFile();
                    break;
                case "stat":
                    request = handler.getStatRequestFromJsonFile();
            }

            Response response = ResponseBuilder.formResponse(daoCustomer, daoProduct, request);

            JsonHandler.writeJsonToFile(args[2], response);

        } catch (IOException ioExceptionIO) {

        } catch (SQLException sqlException) {

        } catch (IllegalArgumentException illegalArgumentException) {

        }
    }
}
