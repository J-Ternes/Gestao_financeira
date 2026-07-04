package com.jonathandev.gestao_financeira.services;
import com.jonathandev.gestao_financeira.dtos.UserRequestDto;
import com.jonathandev.gestao_financeira.dtos.UserResponseDto;
import com.jonathandev.gestao_financeira.exceptions.EmailFoundException;
import com.jonathandev.gestao_financeira.model.UserModel;
import com.jonathandev.gestao_financeira.model.UserRole;
import com.jonathandev.gestao_financeira.repositories.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserModel cadastrar(UserRequestDto dto) {
        UserModel user = userRepository.findByEmail(dto.email());

        if(user != null) throw new EmailFoundException();

        UserModel novoUsuario = new UserModel();
        user.setNome(dto.nome());
        user.setEmail(dto.email());
        user.setSenha(dto.senha());
        user.setRole(UserRole.USER);

        return userRepository.save(novoUsuario);
    }

    public List<UserResponseDto> usuariosCadastrados (){
        List<UserModel> usuarios = userRepository.findAll();
         return usuarios.stream().map(user-> new UserResponseDto(
                 user.getNome(),user.getEmail(),user.getRole())).toList();
    }
}

