package com.jonathandev.gestao_financeira.auth;

import com.jonathandev.gestao_financeira.dtos.AuthRegisterDto;
import com.jonathandev.gestao_financeira.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthRequestDto dados){
        UsernamePasswordAuthenticationToken emailPassword = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());
        var auth = authenticationManager.authenticate(emailPassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid AuthRegisterDto dados){
        authUserService.registrar(dados);

        return ResponseEntity.ok().build();

    }
}
