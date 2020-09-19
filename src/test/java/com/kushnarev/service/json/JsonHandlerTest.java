package com.kushnarev.service.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.kushnarev.service.request.requests.requestImpl.SearchRequest;
import com.kushnarev.service.request.requests.requestImpl.StatRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonHandlerTest {

    private File testFile;

    @Before
    public void setUp() {
        this.testFile = new File("src/test/java/testResources/testStat.json");
    }

    @Test(expected = JsonParseException.class)
    public void test_parseJsonFromFile_with_notRightFile() throws IOException {
        File notJsonFile = new File("src/test/java/testResources/notJson.txt");
        JsonHandler.parseJsonFromFile(notJsonFile);
    }

    @Test
    public void test_parseJsonFromFile() throws IOException {
        assertThat(JsonHandler.parseJsonFromFile(testFile), notNullValue());
    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void test_fromJsonNode_notRightObject() throws IOException {
        JsonNode jsonNode = JsonHandler.parseJsonFromFile(testFile);
        JsonHandler.fromJsonNode(jsonNode, SearchRequest.class);
    }

    @Test
    public void test_fromJsonNode_rightObject() throws IOException {
        JsonNode jsonNode = JsonHandler.parseJsonFromFile(testFile);
        StatRequest stat = JsonHandler.fromJsonNode(jsonNode, StatRequest.class);

        assertThat(stat.getRequestData().getDates().get(0).toString(), is("2020-09-14"));
        assertThat(stat.getRequestData().getDates().get(1).toString(), is("2020-09-26"));
    }
}