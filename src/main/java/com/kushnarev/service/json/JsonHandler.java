package com.kushnarev.service.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kushnarev.service.response.responses.Response;

import java.io.File;
import java.io.IOException;

public class JsonHandler {

    static String outDirectory = "JsonResult/";

    private static final ObjectMapper objectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        /*configurations*/
        defaultObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return defaultObjectMapper;
    }

    public static JsonNode parseJsonFromFile(File file) throws IOException {
        return objectMapper.readTree(file);
    }

    public static <T> T fromJsonNode(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        }catch (JsonProcessingException jsonProcessingException) {
            try {
                throw new IllegalArgumentException(jsonProcessingException.getMessage()
                        .substring(0, jsonProcessingException.getMessage().indexOf("!")));
            } catch (StringIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("query type not correspond with query json file");
            }
        }
    }

    public static void writeResponseToJsonFile(String outputFileName, Response response) throws IOException {
        objectMapper.writeValue(new File(outDirectory + outputFileName), response);
    }
}
