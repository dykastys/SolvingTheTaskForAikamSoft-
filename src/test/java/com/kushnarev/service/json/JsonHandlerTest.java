package com.kushnarev.service.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.kushnarev.entities.Customer;
import com.kushnarev.service.request.criteria.Criteria;
import com.kushnarev.service.request.requests.requestImpl.SearchRequest;
import com.kushnarev.service.request.requests.requestImpl.StatRequest;
import com.kushnarev.service.response.responses.responseImpl.searchResponse.SearchResponse;
import com.kushnarev.service.response.responses.responseImpl.searchResponse.searchResult.SearchResult;
import com.kushnarev.service.response.responses.responseImpl.statResponse.StatResponse;
import com.kushnarev.service.response.responses.responseImpl.statResponse.responseCustomerAndProduct.ResponseCustomer;
import com.kushnarev.service.response.responses.responseImpl.statResponse.responseCustomerAndProduct.ResponseProduct;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    @Test(expected = IllegalArgumentException.class)
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

    @Test
    public void test_writeSearchJsonToFile() throws IOException {
        String outDirectory = JsonHandler.outDirectory;
        JsonHandler.outDirectory = "src/test/java/testResources/testResults/";

        String fileName = "testResult.json";

        Files.deleteIfExists(Paths.get(JsonHandler.outDirectory + fileName));

        SearchResponse response = setUpSearchResponse();
        JsonHandler.writeResponseToJsonFile(fileName, response);

        assertThat(Files.size(Paths.get(JsonHandler.outDirectory + fileName)), is(116L));

        JsonHandler.outDirectory = outDirectory;
    }

    /*5 methods for create SearchResponse*/
    private SearchResponse setUpSearchResponse() {
        SearchResponse response = new SearchResponse();
        response.setType("search");
        response.setResults(getSearchResultList());
        return response;
    }

    private List<SearchResult> getSearchResultList() {
        List<SearchResult> results = new ArrayList<>();
        SearchResult result = getSearchResult();
        results.add(result);
        return results;
    }

    private SearchResult getSearchResult() {
        SearchResult result = new SearchResult();
        result.setCriteria(getCriteria());
        result.setResults(getCustomerList());
        return result;
    }

    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.setBadCustomers(1);
        return criteria;
    }

    private List<Customer> getCustomerList() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Andrey", "Petrov"));
        return customers;
    }

    @Test
    public void test_writeStatJsonToFile() throws IOException {
        String outDirectory = JsonHandler.outDirectory;
        JsonHandler.outDirectory = "src/test/java/testResources/testResults/";

        String fileName = "testResult.json";

        Files.deleteIfExists(Paths.get(fileName));

        StatResponse response = setUpStatResponse();
        JsonHandler.writeResponseToJsonFile(fileName, response);

        assertThat(Files.size(Paths.get(JsonHandler.outDirectory + fileName)), is(168L));

        JsonHandler.outDirectory = outDirectory;
    }

    /*5 methods for create StatResponse*/
    private StatResponse setUpStatResponse() {
        StatResponse response = new StatResponse();
        response.setType("stat");
        response.setTotalDays(3);
        response.setCustomers(getResponseCustomerList());
        long totalExpense = response.getCustomers().stream()
                .mapToLong(
                        x -> x.getPurchases().stream()
                                .mapToLong(ResponseProduct::getExpenses)
                                .sum())
                .sum();
        response.setTotalExpenses(totalExpense);
        response.setAvgExpenses((totalExpense*1.0) / response.getCustomers().size());
        return response;
    }

    private List<ResponseCustomer> getResponseCustomerList() {
        List<ResponseCustomer> customers = new ArrayList<>();
        ResponseCustomer responseCustomer = getResponseCustomer();
        customers.add(responseCustomer);
        return customers;
    }

    private ResponseCustomer getResponseCustomer() {
        ResponseCustomer responseCustomer = new ResponseCustomer();
        responseCustomer.setName("Mat");
        List<ResponseProduct> purchases = getPurchaseList();
        responseCustomer.setPurchases(purchases);
        responseCustomer.setTotalExpenses(purchases.stream().mapToLong(ResponseProduct::getExpenses).sum());
        return responseCustomer;
    }

    private List<ResponseProduct> getPurchaseList() {
        List<ResponseProduct> responseProducts = new ArrayList<>();
        ResponseProduct product = getResponseProduct();
        responseProducts.add(product);
        return responseProducts;
    }

    private ResponseProduct getResponseProduct() {
        ResponseProduct product = new ResponseProduct();
        product.setName("Staff");
        product.setExpenses(1010);
        return product;
    }
}
