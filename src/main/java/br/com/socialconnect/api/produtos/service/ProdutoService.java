package br.com.socialconnect.api.produtos.service;

import br.com.socialconnect.api.exception.RecursoNaoEncontradoException;
import br.com.socialconnect.api.produtos.dto.ProdutoRequestDTO;
import br.com.socialconnect.api.produtos.dto.ProdutoResponseDTO;
import br.com.socialconnect.api.produtos.model.CategoriaProduto;
import br.com.socialconnect.api.produtos.model.Produto;
import br.com.socialconnect.api.produtos.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Page<ProdutoResponseDTO> listar(String nome, CategoriaProduto categoria, Pageable pageable){
        Page<Produto> page;         // filtro exato (prioridade)
        if (nome != null && !nome.isBlank()) {
            page = repository.findByNomeContainingIgnoreCase(nome, pageable);  // filtro parcial
        } else if (categoria != null) {
            page = repository.findByCategoriaContainingIgnoreCase(categoria, pageable);
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
        Produto produto = Produto.builder()
                .dataCadastro(dto.dataCadastro())
                .nomeProduto(dto.nomeProduto())
                .categoriaProduto(dto.categoriaProduto())
                .estoqueAtual(dto.estoqueAtual())
                .estoqueMinimo(dto.estoqueMinimo())
                .unidadeDeMedia(dto.unidadeMedida())
                .build();
        return toResponseDTO(repository.save(produto));
    }

    // PUT (substituição total)
    public ProdutoResponseDTO atualizar(Long idProduto, ProdutoRequestDTO dto) {
        Produto produto = buscarEntidade(idProduto);
        produto.setNomeProduto(dto.nomeProduto());
        produto.setCategoriaProduto(dto.categoriaProduto());
        produto.setEstoqueAtual(dto.estoqueAtual());
        produto.setEstoqueMinimo(dto.estoqueMinimo());
        produto.setUnidadeDeMedia(dto.unidadeMedida());
        return toResponseDTO(repository.save(produto));
    }

    public void deletar(Long idProduto) {
        if (!repository.existsById(idProduto)) {
            throw new RecursoNaoEncontradoException("Produto não encontrada: " + idProduto);
        }
        repository.deleteById(idProduto);
    }

    private Produto buscarEntidade(Long idProduto) {
        return repository.findById(idProduto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrada: " + idProduto));
    }

    // Mapeador Entity -> Response DTO
    private ProdutoResponseDTO toResponseDTO(Produto p) {
        return new ProdutoResponseDTO(
                p.getIdProduto(),
                p.getNomeProduto(),
                p.getCategoriaProduto(),
                p.getEstoqueAtual(),
                p.getEstoqueMinimo(),
                p.getUnidadeDeMedia(),
                p.getDataCadastro()
        );
    }
}
