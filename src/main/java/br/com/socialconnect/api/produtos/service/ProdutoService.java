package br.com.socialconnect.api.produtos.service;

import br.com.socialconnect.api.exception.ConflitoException;
import br.com.socialconnect.api.exception.RecursoNaoEncontradoException;
import br.com.socialconnect.api.produtos.dto.ProdutoRequestDTO;
import br.com.socialconnect.api.produtos.dto.ProdutoResponseDTO;
import br.com.socialconnect.api.produtos.model.CategoriaProduto;
import br.com.socialconnect.api.produtos.model.Produto;
import br.com.socialconnect.api.produtos.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Page<ProdutoResponseDTO> listar(String nome, CategoriaProduto categoria, Pageable pageable){
        Page<Produto> page;
        if (nome != null && !nome.isBlank()) {
            page = repository.findByNomeContainingIgnoreCase(nome, pageable);
        } else if (categoria != null) {
            page = repository.findByCategoria(categoria, pageable);
        } else {
            page = repository.findAll(pageable);
        }
        return page.map(this::toResponseDTO);
    }

    public ProdutoResponseDTO buscarPorId(Long idProduto) {
        return toResponseDTO(buscarEntidade(idProduto));
    }

    // POST
    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        if (repository.existsByNome(dto.nomeProduto())) {
            throw new ConflitoException("Já existe um produto cadastrado com este nome.");
        }

        Produto produto = Produto.builder()
                .dataCadastro(LocalDate.now())
                .nome(dto.nomeProduto())
                .categoria(dto.categoriaProduto())
                .estoqueAtual(dto.estoqueAtual())
                .estoqueMinimo(dto.estoqueMinimo())
                .unidadeMedida(dto.unidadeMedida())
                .build();
        return toResponseDTO(repository.save(produto));
    }

    // PUT (substituição total)
    public ProdutoResponseDTO atualizar(Long idProduto, ProdutoRequestDTO dto) {
        Produto produto = buscarEntidade(idProduto);

        // Verifica se o novo nome já pertence a outro produto
        if (repository.existsByNomeAndIdProdutoNot(dto.nomeProduto(), idProduto)) {
            throw new ConflitoException("Já existe outro produto cadastrado com este nome.");
        }

        produto.setNome(dto.nomeProduto());
        produto.setCategoria(dto.categoriaProduto());
        produto.setEstoqueAtual(dto.estoqueAtual());
        produto.setEstoqueMinimo(dto.estoqueMinimo());
        produto.setUnidadeMedida(dto.unidadeMedida());
        return toResponseDTO(repository.save(produto));
    }

    public void deletar(Long idProduto) {
        if (!repository.existsById(idProduto)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado: " + idProduto);
        }
        repository.deleteById(idProduto);
    }

    private Produto buscarEntidade(Long idProduto) {
        return repository.findById(idProduto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: " + idProduto));
    }

    // Mapeador Entity -> Response DTO
    private ProdutoResponseDTO toResponseDTO(Produto p) {
        boolean estoqueBaixo = p.getEstoqueAtual() < p.getEstoqueMinimo();

        return new ProdutoResponseDTO(
                p.getIdProduto(),
                p.getNome(),
                p.getCategoria(),
                p.getEstoqueAtual(),
                p.getEstoqueMinimo(),
                p.getUnidadeMedida(),
                p.getDataCadastro(),
                estoqueBaixo
        );
    }
}