package com.jonathandev.gestao_financeira.hendler;

import com.jonathandev.gestao_financeira.dtos.ErrorResponseDto;
import com.jonathandev.gestao_financeira.exceptions.CategoriaFoundException;
import com.jonathandev.gestao_financeira.exceptions.EmailFoundException;
import com.jonathandev.gestao_financeira.exceptions.UserNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHendler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailFoundException.class)
    public ResponseEntity<ErrorResponseDto> emailFoundExceptionHandler (EmailFoundException userFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(400,"Esse email já está cadastrado", LocalDateTime.now()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> userNotFoundHandler (UserNotFoundException userNotFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(400,"Usuário não encontrado",LocalDateTime.now()));
    }

    @ExceptionHandler(CategoriaFoundException.class)
    public ResponseEntity<ErrorResponseDto> categoriaFoundExceptionHandler (CategoriaFoundException categoriaFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(400, "Categoria já cadastrada", LocalDateTime.now()));

    }
}
