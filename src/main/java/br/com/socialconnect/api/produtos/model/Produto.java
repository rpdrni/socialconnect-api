package br.com.socialconnect.api.produtos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(name = "nome_produto", nullable = false, length = 150)
    private String nomeProduto;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_produto", nullable = false, length = 20)
    private CategoriaProduto categoriaProduto;

    @Column(name = "estoque_atual", nullable = false)
    private Integer estoqueAtual;

    @Column(name = "estoque_minimo", nullable = false)
    private Integer estoqueMinimo;

    @Column(name = "unidade_medida", nullable = false)
    private String unidadeDeMedia;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;
}
