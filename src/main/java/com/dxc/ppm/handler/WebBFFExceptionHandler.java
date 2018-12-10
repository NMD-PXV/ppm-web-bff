package com.dxc.ppm.handler;

import com.dxc.ppm.exception.WebBFFException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebBFFExceptionHandler {
    @ExceptionHandler(WebBFFException.class)
    public ResponseEntity<String> patientExceptionHandler(WebBFFException ex) {
        String message = ex.getResponse().name();
        return new ResponseEntity<>(message, ex.getResponse().getHttpStatus());
    }
}
