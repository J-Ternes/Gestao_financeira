package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    //Esse meetodo faz com que o Spring Security verifique o BD dos nossos usuários
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails usuario = userRepository.findByEmail(username);

        if (usuario == null) throw new UsernameNotFoundException("Usuário não encontrado");

        return usuario;
    }
}
