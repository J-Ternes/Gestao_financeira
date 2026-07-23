package com.jonathandev.gestao_financeira.helpers;

import com.jonathandev.gestao_financeira.constants.PaginacaoConstantes;
import com.jonathandev.gestao_financeira.exceptions.ParametroInvalidoException;

import java.util.List;

public final class PaginacaoUtils {

    private PaginacaoUtils(){}

    public static void validarNumeroPaginas(int pagina){

        if(pagina < 0) throw new ParametroInvalidoException("O número de páginas não pode ser negativo");

        if(pagina > PaginacaoConstantes.LIMITE_PAGINA) {
            throw new ParametroInvalidoException("O número de páginas solicitado não pode ser maior que " + PaginacaoConstantes.LIMITE_PAGINA + ".");
        }
    }

    //Aqui eu sei que é possivel melhorar o desempenho (BigO notation) -> Estudar a diferença do BigO notation de List (Array) e Map;
    public static void validarOrdenarPor(String ordenarPor) {

        normalizarOrdenarPor(ordenarPor); //Para normalizar e ser ignoraseCase

        List<String> camposPermitidos = List.of("categoria", "criadoEm");

        boolean valido = camposPermitidos.stream()
                .anyMatch(campo -> campo.equals(ordenarPor));

        if (!valido) {
            throw new ParametroInvalidoException(
                    "O campo de ordenação '" + ordenarPor + "' é inválido. Os campos permitidos são: " + camposPermitidos + ".");
        }
    }

    //Aqui eu sei que é possivel melhorar o desempenho (BigO notation) -> Estudar a diferença do BigO notation de List (Array) e Map;
    private static String normalizarOrdenarPor(String ordenarPor) {
        if (ordenarPor.equalsIgnoreCase("categoria")) return "categoria";
        if (ordenarPor.equalsIgnoreCase("criadoEm")) return "criadoEm";
        return ordenarPor;
    }

}
