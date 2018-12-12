package com.dxc.ppm.webbff.handler;

import com.dxc.ppm.webbff.exception.BffException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BffExceptionHandler {
    @ExceptionHandler(BffException.class)
    public ResponseEntity<String> patientExceptionHandler(BffException ex) {
        String message = ex.getResponse().name();
        return new ResponseEntity<>(message, ex.getResponse().getHttpStatus());
    }
}
