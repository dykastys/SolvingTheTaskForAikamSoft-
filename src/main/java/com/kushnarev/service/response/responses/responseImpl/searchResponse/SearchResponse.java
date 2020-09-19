package com.kushnarev.service.response.responses.responseImpl.searchResponse;

import com.kushnarev.service.response.responses.Response;
import com.kushnarev.service.response.responses.responseImpl.searchResponse.searchResult.SearchResult;

import java.util.List;

public class SearchResponse implements Response {
    private String type;
    private List<SearchResult> results;

    public SearchResponse() { }

    public SearchResponse(String type, List<SearchResult> results) {
        this.type = type;
        this.results = results;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }
}
