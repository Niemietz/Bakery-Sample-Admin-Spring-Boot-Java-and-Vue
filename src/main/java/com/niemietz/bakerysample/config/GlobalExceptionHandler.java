package com.niemietz.bakerysample.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> recursoNaoEncontrado(ResourceNotFoundException ex, HttpServletRequest request) {
        Map<String, Object> body = Map.of(
            "timestamp", Instant.now(),
            "status", 404,
            "error", "Not Found",
            "message", ex.getMessage(),
            "path", ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest().getRequestURI()
        );
        return ResponseEntity.status(404).body(body);
    }

    // 404 genérico do Spring (quando não tem controller)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> paginaNaoEncontrada(NoHandlerFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(404).body(Map.of(
            "error", "Endpoint não encontrado",
            "message", "Verifique a URL da API"
        ));
    }
}