package br.com.socialconnect.api.produtos.dto;

import br.com.socialconnect.api.produtos.model.CategoriaProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

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
        String unidadeDeMedia,
        @Schema(description = "Data de cadastro (gerada pelo servidor)", example = "2026-09-14")
        LocalDate dataCadastro
) {
}
