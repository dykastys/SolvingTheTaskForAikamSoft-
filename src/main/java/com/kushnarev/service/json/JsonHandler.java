package com.kushnarev.service.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonHandler {

    private static final ObjectMapper objectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        /*configurations*/
        return defaultObjectMapper;
    }

    public static JsonNode parseJsonFromFile(File file) throws IOException {
        return objectMapper.readTree(file);
    }

    public static <T> T fromJsonNode(JsonNode node, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }
}
