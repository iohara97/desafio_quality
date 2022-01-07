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

/**
 * Classe que captura e lida com exceções prédeterminadas dando um tratamento
 * personalizado
 * */
@RestControllerAdvice
public class ControllerAdvice {

    /**
     * O handler usado para tratar as validações nos DTOs
     * @param ex (MethodArgumentNotValidException)
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
     * @param ex (HttpMessageNotReadableException)
     * @param request (WebRequest)
     * @return HTTPResponse 400 com a mensagem de erro (String)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHTTPMessageNotReadableExceptions(HttpMessageNotReadableException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Houve erro ao tentar serializar um campo.");
    }

    /**
     * O handler usado para tratar erro na validação das chaves do Bady Request
     * @param ex (PayloadException)
     * @param request (WebRequest)
     * @return HTTPResponse 400 com a mensagem de erro (String)
     */
    @ExceptionHandler(PayloadException.class)
    protected ResponseEntity<Object> handlePayloadException(PayloadException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Campo não reconhecido.");
    }

    /**
     * O handler usado para tratar a persistência de uma casa sem cômodos.
     * @param ex (ComodosVazioException)
     * @param request (WebRequest)
     * @return HTTPResponse 400 com a mensagem de erro (String)
     */
    @ExceptionHandler(ComodosVazioException.class)
    protected ResponseEntity<Object> handleComodosVazioException(ComodosVazioException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * O handler usado para tratar a verificação de existência de um bairro.
     * @param ex (BusinessException)
     * @param request (WebRequest)
     * @return HTTPResponse 400 com a mensagem de erro (String)
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
