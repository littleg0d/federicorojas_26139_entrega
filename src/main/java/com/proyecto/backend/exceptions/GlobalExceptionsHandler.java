package com.proyecto.backend.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionsHandler {
    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundException(NoEncontrado ex) {
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleCategoriaEnUsoException(CategoriaEnUso ex) {
        return ResponseEntity
                .status(409)
                .body(ex.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> validationHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors())
            errores.put(error.getField(), error.getDefaultMessage());

        return ResponseEntity
                .status(400)
                .body(errores);

    }
}
