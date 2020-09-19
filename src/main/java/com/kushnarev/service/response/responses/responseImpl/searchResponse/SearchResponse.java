package com.kushnarev.service.response.responses.responseImpl.searchResponse;

import com.kushnarev.service.response.responses.Response;

import java.util.List;

public class SearchResponse implements Response {
    private String type;
    private List<Result> results;

    public SearchResponse() { }

    public SearchResponse(String type, List<Result> results) {
        this.type = type;
        this.results = results;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
