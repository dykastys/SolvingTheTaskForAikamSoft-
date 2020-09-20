package com.kushnarev.service.response.responses.responseImpl.errorResponse;

import com.kushnarev.service.response.responses.Response;

public class ErrorResponse implements Response {
    private String type;
    private String message;

    public ErrorResponse() { }

    public ErrorResponse(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
