package br.com.socialconnect.api.produtos.dto;

import br.com.socialconnect.api.produtos.model.CategoriaProduto;
import br.com.socialconnect.api.validation.DataNaoFutura;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Produto retornado pela API")
public record ProdutoResponseDTO(
        @Schema(example = "1")
        Long idProduto,
        @Schema(description = "Identificador do produto", example = "1")
        String nomeProduto,
        @Schema(example = "ALIMENTO")
        CategoriaProduto categoriaProduto,
        @Schema(example = "30")
        Integer estoqueAtual,
        @Schema(example = "1")
        Integer estoqueMinimo,
        @Schema(example = "kg")
        String unidadeMedida,
        @Schema(description = "Data em que o produto foi cadastrado", example = "2026-09-14")
        @NotNull(message = "{NotNull.dataCadastro}")
        @DataNaoFutura(message = "{DataNaoFutura.dataCadastro}")
        LocalDate dataCadastro,
        @Schema(description = "Indica se o estoque atual está abaixo do mínimo", example = "false")
        boolean estoqueBaixo
) {
}
