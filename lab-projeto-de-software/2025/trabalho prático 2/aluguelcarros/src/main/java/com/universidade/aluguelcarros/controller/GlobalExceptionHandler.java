package com.universidade.aluguelcarros.controller;

import com.universidade.aluguelcarros.application.exception.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> recursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validacaoFalhou(MethodArgumentNotValidException ex) {
        var erros = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(
                org.springframework.validation.FieldError::getField,
                e -> e.getDefaultMessage() != null ? e.getDefaultMessage() : "valor inválido",
                (a, b) -> a
            ));
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of("erro", "Validação falhou", "detalhes", erros));
    }
}
