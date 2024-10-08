package com.api.pedidosApi.controllers.exception;

import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ResourceExceptionHandler {
        @ExceptionHandler(ObjectNotFoundException.class)
        public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, ServerHttpRequest request){

            StandardError  err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),System.currentTimeMillis());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }

}

