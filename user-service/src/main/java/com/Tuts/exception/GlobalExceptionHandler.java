package com.Tuts.exception;

import java.time.LocalDateTime;

import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.Tuts.payload.response.ExceptionResponse;

import io.micrometer.core.ipc.http.HttpSender.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    // this method will handle all the exceptions thrown by the application
    // and return a response entity with the exception message and the request
    public ResponseEntity<ExceptionResponse> ExceptionHandler(Exception e, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                request.getDescription(false), // so we don't use see full default string error (unreadable)
                LocalDateTime.now());

        return ResponseEntity.ok(response);
    }
}
