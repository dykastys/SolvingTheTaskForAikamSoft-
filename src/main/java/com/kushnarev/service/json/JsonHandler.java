package com.kushnarev.service.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kushnarev.service.response.responses.Response;

import java.io.File;
import java.io.IOException;

public class JsonHandler {

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

    public static <T> T fromJsonNode(JsonNode node, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }

    public static void writeJsonToFile(String outputFileName, Response response) throws IOException {
        objectMapper.writeValue(new File(outputFileName), response);
    }
}
