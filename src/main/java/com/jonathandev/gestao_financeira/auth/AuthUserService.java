package com.jonathandev.gestao_financeira.auth;

import com.jonathandev.gestao_financeira.dtos.AuthRegisterDto;
import com.jonathandev.gestao_financeira.dtos.UserResponseDto;
import com.jonathandev.gestao_financeira.exceptions.EmailFoundException;
import com.jonathandev.gestao_financeira.model.UserModel;
import com.jonathandev.gestao_financeira.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final UserRepository userRepository;

    public UserModel registrar(AuthRegisterDto dto){
        UserDetails usuario = userRepository.findByEmail(dto.email());
        if(usuario != null) throw new EmailFoundException();

        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        UserModel novoUsuario = new UserModel(dto.email(), senhaCriptografada, dto.role());

        return userRepository.save(novoUsuario);

    }

}
