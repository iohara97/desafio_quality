package br.com.meli.spring3.demo.controller;

import exception.BusinessException;
import exception.ComodosVazioException;
import exception.PayloadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHTTPMessageNotReadableExceptions(HttpMessageNotReadableException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Houve erro ao tentar serializar um campo.");
    }

    @ExceptionHandler(PayloadException.class)
    protected ResponseEntity<Object> handlePayloadException(PayloadException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Campo não reconhecido.");
    }

    @ExceptionHandler(ComodosVazioException.class)
    protected ResponseEntity<Object> handleComodosVazioException(ComodosVazioException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
