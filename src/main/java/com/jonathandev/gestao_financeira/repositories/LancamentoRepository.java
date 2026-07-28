package com.jonathandev.gestao_financeira.repositories;

import com.jonathandev.gestao_financeira.dtos.ValorTotalPorCategoriaResponseDto;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, UUID> {

    Page<LancamentoModel> findByUsuarioId(UUID usuarioId, Pageable pageable);

    @Query("SELECT l FROM LancamentoModel l WHERE l.categoria.categoria = :nomeCategoria AND l.usuario.id = :usuarioId")
    Page<LancamentoModel> findByCategoriaAndUsuarioNomePaginado(@Param("nomeCategoria") String nomeCategoria, UUID usuarioId, Pageable pageable);

    @Query("SELECT SUM(l.preco) FROM LancamentoModel l WHERE l.categoria.categoria = :nomeCategoria AND l.usuario.id = :usuarioId")
    BigDecimal calcularTotalPorCategoriaAndUsuario(@Param("nomeCategoria") String nomeCategoria, UUID usuarioId);

    @Query("SELECT new com.jonathandev.gestao_financeira.dtos.ValorTotalPorCategoriaResponseDto(l.categoria.categoria, SUM(l.preco)) " +
            "FROM LancamentoModel l " +
            "WHERE l.usuario.id = :usuarioId " +
            "GROUP BY l.categoria.categoria")
    List<ValorTotalPorCategoriaResponseDto> calcularTotalPorTodasCategorias(@Param("usuarioId") UUID usuarioId);

}
