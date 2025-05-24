package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entities.Produto;
import com.example.demo.repository.IProdutoRepository;

@Service
public class ProdutoService {

    private final IProdutoRepository produtoRepository;

    public ProdutoService(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto createProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoById(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto updateProduto(Long id, Produto produtoDetails) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoDetails.getNome());
            produto.setDescricao(produtoDetails.getDescricao());
            produto.setQuantidade(produtoDetails.getQuantidade());
            produto.setPrecoUnitario(produtoDetails.getPrecoUnitario());
            produto.setCategoriaId(produtoDetails.getCategoriaId());
            return produtoRepository.save(produto);
        }).orElseThrow(() -> new RuntimeException("Produto not found with id " + id));
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
