package com.dxc.ppm.exception;

import com.dxc.ppm.common.WebBFFStorageError;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WebBFFException extends RuntimeException{

    private final WebBFFStorageError response;

    public WebBFFException(WebBFFStorageError response) {
        this(response, null, new Object[0]);
    }

    public WebBFFException(WebBFFStorageError response, Throwable cause, Object... params) {
        super(response + Arrays.stream(params).map(Object::toString).collect(Collectors.joining(";", "{", "}")));
        this.response = response;
    }

    public WebBFFException(WebBFFStorageError response, Object... params) {
        this(response, null, params);
    }

    public WebBFFStorageError getResponse() {
        return response;
    }
}
