package com.jonathandev.gestao_financeira.hendler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.jonathandev.gestao_financeira.dtos.ErrorResponseDto;
import com.jonathandev.gestao_financeira.exceptions.*;
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(404,"Usuário não encontrado",LocalDateTime.now()));
    }

    @ExceptionHandler(CategoriaFoundException.class)
    public ResponseEntity<ErrorResponseDto> categoriaFoundExceptionHandler (CategoriaFoundException categoriaFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(400, "Categoria já cadastrada", LocalDateTime.now()));
    }

    @ExceptionHandler(CategoriaNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> categoriaNotFoundExceptionHandler (CategoriaNotFoundException categoriaNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(404, "Categoria não cadastrada", LocalDateTime.now()));
    }

    @ExceptionHandler(LancamentoNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> lancamentoNotFoundHandler(LancamentoNotFoundException lancamentoNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(404,"Lançamento não encontrado", LocalDateTime.now()));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponseDto> tokenExpiredHandler(TokenExpiredException tokenExpiredException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto(401,"Sua sessão expirou. Realize o login novamente",LocalDateTime.now()));
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ErrorResponseDto> invalidTokenHandler (JWTVerificationException jwtVerificationException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto(HttpStatus.UNAUTHORIZED.value(),"Token inválido",LocalDateTime.now()));
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> emailNotFoundhandler( EmailNotFoundException emailNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), "Email não cadastrado", LocalDateTime.now()));
    }

    @ExceptionHandler(IncompatibleUserException.class)
    public ResponseEntity<ErrorResponseDto> acesshandler (IncompatibleUserException acessException){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseDto(HttpStatus.FORBIDDEN.value(), "Você não tem permissão para essa ação", LocalDateTime.now()));
    }
}
