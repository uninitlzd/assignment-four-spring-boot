package com.alfredoeka.assignmentfour.controller;

import com.alfredoeka.assignmentfour.model.APIResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {
    public ResponseEntity<APIResponse<String>> constraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(APIResponse.<String>builder()
                        .data(exception.getMessage())
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .build());
    }

    public ResponseEntity<APIResponse<String>> apiException(ResponseStatusException exception) {
        HttpStatusCode httpStatusCode = exception.getStatusCode();
        return ResponseEntity.status(exception.getStatusCode())
                .body(APIResponse.<String>builder()
                        .data(exception.getMessage())
                        .code(httpStatusCode.value())
                        .status(exception.getReason())
                        .build());
    }
}
