package com.dxc.ppm.webbff.advise;

import com.dxc.ppm.webbff.exception.BffException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(BffException.class)
    public ResponseEntity<String> patientExceptionHandle(BffException ex) {
        String message = ex.getResponse().name();
        return new ResponseEntity<>(message, ex.getResponse().getHttpStatus());
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> jsonProcessingHandle(JsonProcessingException ex){
        return new ResponseEntity<>("Can't parse json", HttpStatus.BAD_REQUEST);
    }
}
