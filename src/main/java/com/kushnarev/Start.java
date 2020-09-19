package com.kushnarev;

import com.kushnarev.service.request.requestHandler.RequestHandler;
import com.kushnarev.service.request.requestHandler.RequestHandlerImpl;
import com.kushnarev.service.request.requests.Request;
import com.kushnarev.service.response.ResponseHandler;
import com.kushnarev.service.response.responses.Response;

import java.io.IOException;
import java.sql.SQLException;

public class Start {
    public static void main(String[] args) throws IOException, SQLException {
        RequestHandler handler = new RequestHandlerImpl(args[1]);

        Request request = null;

        switch (args[0]) {
            case "search":
                request = handler.getSearchRequestFromJsonFile();
                break;
            case "stat":
                request = handler.getStatRequestFromJsonFile();
        }

        ResponseHandler responseHandler = new ResponseHandler(request);
        Response response = responseHandler.formResponse();
    }
}
