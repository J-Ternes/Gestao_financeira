package com.jonathandev.gestao_financeira.services;
import com.jonathandev.gestao_financeira.dtos.UserRequestDto;
import com.jonathandev.gestao_financeira.dtos.UserResponseDto;
import com.jonathandev.gestao_financeira.exceptions.EmailFoundException;
import com.jonathandev.gestao_financeira.exceptions.UserNotFoundException;
import com.jonathandev.gestao_financeira.model.UserModel;
import com.jonathandev.gestao_financeira.model.UserRole;
import com.jonathandev.gestao_financeira.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserModel cadastrar(UserRequestDto dto) {
        UserDetails user = userRepository.findByEmail(dto.email());

        if(user != null) throw new EmailFoundException();

        UserModel novoUsuario = new UserModel();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setSenha(dto.senha());
        novoUsuario.setRole(UserRole.USER);

        return userRepository.save(novoUsuario);
    }

    public List<UserResponseDto> usuariosCadastrados (){
        List<UserModel> usuarios = userRepository.findAll();
         return usuarios.stream().map(user-> new UserResponseDto(
                 user.getNome(),user.getEmail(),user.getRole())).toList();
    }

    public void deletar(UUID id){
        UserModel usuario = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException());

        userRepository.delete(usuario);

    }
}

