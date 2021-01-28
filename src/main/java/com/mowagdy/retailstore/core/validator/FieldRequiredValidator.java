package com.mowagdy.retailstore.core.validator;

import com.mowagdy.retailstore.core.exception.FieldRequiredException;

public class FieldRequiredValidator<T> implements BaseValidator {

    private T value;
    private String key;

    public FieldRequiredValidator(T value, String key) {
        this.value = value;
        this.key = key;
    }

    @Override
    public void validateOrThrow() throws FieldRequiredException {

        boolean result = false;

        if (value instanceof String) {
            result = !value.equals("");
        } else if (value instanceof Long) {
            result = !value.equals(0L);
        } else if (value instanceof Integer) {
            result = !value.equals(0);
        } else if (value instanceof Double) {
            result = !value.equals(0.0);
        } else if (value instanceof Enum) {
            result = !value.toString().toLowerCase().equals("none");
        }

        if (!result) {
            throw new FieldRequiredException(key, "Field [" + key + "] is required");
        }
    }
}
