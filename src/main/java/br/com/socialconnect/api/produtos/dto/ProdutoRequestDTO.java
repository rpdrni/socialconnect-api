package br.com.socialconnect.api.produtos.dto;

import br.com.socialconnect.api.produtos.model.CategoriaProduto;
import br.com.socialconnect.api.validation.DataNaoFutura;
import br.com.socialconnect.api.validation.EstoqueNaoNegativo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProdutoRequestDTO(
        @NotBlank(message = "{produto.nome.obrigatorio}")
        @Size(max = 150)
        String nomeProduto,

        @NotNull(message = "{produto.categoria.obrigatoria}")
        CategoriaProduto categoriaProduto,

        @Min(value = 0, message = "{produto.estoque.minimo}")
        @EstoqueNaoNegativo
        Integer estoqueAtual,

        @Min(value = 0)
        Integer estoqueMinimo,

        @NotBlank
        @Size(max = 20)
        String unidadeMedida
) {
}
