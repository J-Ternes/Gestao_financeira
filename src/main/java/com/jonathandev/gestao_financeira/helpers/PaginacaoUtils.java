package com.jonathandev.gestao_financeira.helpers;

import com.jonathandev.gestao_financeira.constants.PaginacaoConstantes;
import com.jonathandev.gestao_financeira.exceptions.ParametroInvalidoException;

public final class PaginacaoUtils {

    private PaginacaoUtils(){}

    public static void validarNumeroPaginas(int pagina){

        if(pagina < 0) throw new ParametroInvalidoException("O número de páginas não pode ser negativo");

        if(pagina > PaginacaoConstantes.LIMITE_PAGINA) {
            throw new ParametroInvalidoException("O número de páginas solicitado não pode ser maior que " + PaginacaoConstantes.LIMITE_PAGINA + ".");
        }


    }

}
