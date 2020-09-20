package com.kushnarev.service.request.requestHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.kushnarev.service.json.JsonHandler;
import com.kushnarev.service.request.requests.requestImpl.SearchRequest;
import com.kushnarev.service.request.requests.requestImpl.StatRequest;


import java.io.File;
import java.io.IOException;

public class RequestHandlerImpl implements RequestHandler {

    private JsonNode jsonNode;

    RequestHandlerImpl() { }

    public RequestHandlerImpl(String fileName) throws IOException {
        this.jsonNode = JsonHandler
                .parseJsonFromFile(
                        new File(String.format("JsonInputData/%s", fileName)));
    }

    @Override
    public SearchRequest getSearchRequestFromJsonFile() throws IOException {
        return JsonHandler.fromJsonNode(jsonNode, SearchRequest.class);
    }

    @Override
    public StatRequest getStatRequestFromJsonFile() throws IOException {
        return JsonHandler.fromJsonNode(jsonNode, StatRequest.class);
    }

    void setJsonNode(JsonNode jsonNode) {
        this.jsonNode = jsonNode;
    }

    JsonNode getJsonNode() {
        return jsonNode;
    }
}
