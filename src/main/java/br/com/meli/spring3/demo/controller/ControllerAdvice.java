package br.com.meli.spring3.demo.controller;

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

/**
 * Classe que captura e lida com exceções prédeterminadas dando um tratamento
 * personalizado
 * */
@RestControllerAdvice
public class ControllerAdvice {

    /**
     * O handler usado para tratar as validações nos DTOs
     * @param ex
     * @return HttpResponse 400 com a mensagem de erro
     */
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

    /**
     * O handler usado para tratar erro na validação dos valores do Body Request
     * @param ex
     * @param request
     * @return HttpResponse 400 com a mensagem de erro
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHTTPMessageNotReadableExceptions(HttpMessageNotReadableException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Houve erro ao tentar serializar um campo.");
    }

    /**
     * O handler usado para tratar erro na validação das chaves do Bady Request
     * @param ex
     * @param request
     * @return HttpResponse 400 com a mensagem de erro
     */
    @ExceptionHandler(PayloadException.class)
    protected ResponseEntity<Object> handlePayloadException(PayloadException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Campo não reconhecido.");
    }

}
