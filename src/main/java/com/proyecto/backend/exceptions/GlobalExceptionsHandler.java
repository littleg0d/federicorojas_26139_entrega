package com.proyecto.backend.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionsHandler {
    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundException(NoEncontrado ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleCategoriaEnUsoException(CategoriaEnUso ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }
}
