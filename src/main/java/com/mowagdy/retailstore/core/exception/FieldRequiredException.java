package com.mowagdy.retailstore.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldRequiredException extends RuntimeException {

    private Map<String, String> arguments = new HashMap<>();

    public FieldRequiredException() {
        super();
    }

    public FieldRequiredException(String field, String message) {
        super(message);
        arguments.put("field", field);
    }

    public FieldRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public Map<String, String> getArguments() {
        return arguments;
    }
}
