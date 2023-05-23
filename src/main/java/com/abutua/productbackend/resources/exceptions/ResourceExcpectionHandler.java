package com.abutua.productbackend.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExcpectionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationsErrors> validationException(MethodArgumentNotValidException exception , 
                                                             HttpServletRequest request){
        
        ValidationsErrors error = new ValidationsErrors();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        error.setError("Validation Error");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(status.value());
        error.setTimeStamp(Instant.now());

        exception.getBindingResult().getFieldErrors().forEach(e -> error.addError(e.getDefaultMessage()));
        
        return ResponseEntity.status(status).body(error);

    }
}

/*
 * O código é um tratador de exceções criado para lidar com exceções do tipo
 * MethodArgumentNotValidException em uma aplicação Spring Boot.
 * 
 * O método validationException recebe como parâmetros a exceção
 * MethodArgumentNotValidException e o objeto HttpServletRequest. Ele retorna
 * uma ResponseEntity com um objeto StandardError que contém informações sobre a
 * exceção, como mensagem, status HTTP, URI da requisição e timestamp.
 * 
 * O @ControllerAdvice é uma anotação usada para definir classes globais que
 * podem ser aplicadas em toda a aplicação para tratar exceções.
 */