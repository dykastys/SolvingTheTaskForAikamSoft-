package com.kushnarev.service.request.requestHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.kushnarev.service.json.JsonHandler;
import com.kushnarev.service.request.criteria.CriteriaType;
import com.kushnarev.service.request.requests.requestImpl.SearchRequest;
import com.kushnarev.service.request.requests.requestImpl.StatRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RequestHandlerImplTest {

    private RequestHandlerImpl requestHandler;

    private File testFile;
    private JsonNode jsonNode;

    @Before
    public void setUp() throws IOException {
        this.testFile = new File("src/test/java/testResources/testStat.json");
        this.jsonNode = JsonHandler.parseJsonFromFile(testFile);

        this.requestHandler = new RequestHandlerImpl();
        this.requestHandler.setJsonNode(jsonNode);
    }

    @Test
    public void test_getJsonNode() {
        assertThat(requestHandler.getJsonNode(), is(jsonNode));
    }

    @Test
    public void test_getStatRequestFromJsonFile() throws IOException {
        StatRequest statRequest = requestHandler.getStatRequestFromJsonFile();

        assertThat(statRequest.getRequestData().getDates().get(0).toString(), is("2020-09-14"));
        assertThat(statRequest.getRequestData().getDates().get(1).toString(), is("2020-09-26"));
    }

    @Test
    public void test_getSearchRequestFromJsonFile() throws IOException {
        this.testFile = new File("src/test/java/testResources/testSearch.json");
        this.jsonNode = JsonHandler.parseJsonFromFile(testFile);
        this.requestHandler.setJsonNode(jsonNode);
        SearchRequest searchRequest = requestHandler.getSearchRequestFromJsonFile();

        assertThat(searchRequest.getRequestData().getCriterias().size(), is(4));
        assertThat(searchRequest.getRequestData().getCriterias().get(1).getType(), is(CriteriaType.PRODUCT_NAME));
    }
}