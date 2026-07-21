package com.jonathandev.gestao_financeira.helpers;

import com.jonathandev.gestao_financeira.model.UserModel;
import org.springframework.security.core.context.SecurityContextHolder;

public class Helpers {

    public UserModel getUsuarioAutenticado() {
        return (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
