package com.dxc.ppm.exception;

import com.dxc.ppm.common.BffError;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BffException extends RuntimeException{

    private final BffError response;

    public BffException(BffError response) {
        this(response, null, new Object[0]);
    }

    public BffException(BffError response, Throwable cause, Object... params) {
        super(response + Arrays.stream(params).map(Object::toString).collect(Collectors.joining(";", "{", "}")));
        this.response = response;
    }

    public BffException(BffError response, Object... params) {
        this(response, null, params);
    }

    public BffError getResponse() {
        return response;
    }
}
