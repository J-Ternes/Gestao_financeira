package com.jonathandev.gestao_financeira.hendler;

import com.jonathandev.gestao_financeira.dtos.ErrorResponseDto;
import com.jonathandev.gestao_financeira.exceptions.EmailFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHendler extends ResponseEntityExceptionHandler {

    public ResponseEntity<ErrorResponseDto> emailFoundExceptionHandler (EmailFoundException userFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(400,"Esse email já está cadastrado", LocalDateTime.now()));
    }
}
