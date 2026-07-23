package com.jonathandev.gestao_financeira.helpers;

import com.jonathandev.gestao_financeira.model.UserModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public final class Helpers {

    private Helpers(){} //Não instanciar

    public static UserModel getUsuarioAutenticado() {
        return (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
