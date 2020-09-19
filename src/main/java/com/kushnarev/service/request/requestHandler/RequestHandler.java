package com.kushnarev.service.request.requestHandler;

import com.kushnarev.service.request.requests.requestImpl.SearchRequest;
import com.kushnarev.service.request.requests.requestImpl.StatRequest;

import java.io.IOException;

public interface RequestHandler {
    SearchRequest getSearchRequestFromJsonFile() throws IOException;
    StatRequest getStatRequestFromJsonFile() throws IOException;
}
