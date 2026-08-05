package com.jonathandev.gestao_financeira.utilities;

import com.jonathandev.gestao_financeira.dtos.TotalPorTipoDto;
import com.jonathandev.gestao_financeira.model.TipoLancamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BuscaNoBanco {

    public BigDecimal buscarTotal(List<TotalPorTipoDto> lista, TipoLancamento tipo) {

        return lista.stream()
                .filter(t -> t.tipo() == tipo)
                .map(TotalPorTipoDto::total)
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }
}
