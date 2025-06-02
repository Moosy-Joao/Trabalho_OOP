package com.inventory.service;

import com.inventory.dto.ProdutoDTO;
import com.inventory.entity.Categoria;
import com.inventory.entity.Produto;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.exception.BusinessException;
import com.inventory.repository.CategoriaRepository;
import com.inventory.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    
    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }
    
    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
        if (produtoRepository.existsByNome(produtoDTO.getNome())) {
            throw new BusinessException("Já existe um produto com este nome");
        }
        
        Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + produtoDTO.getCategoriaId()));
        
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setPrecoUnitario(produtoDTO.getPrecoUnitario());
        produto.setCategoria(categoria);
        
        Produto produtoSalvo = produtoRepository.save(produto);
        return convertToDTO(produtoSalvo);
    }
    
    @Transactional(readOnly = true)
    public List<ProdutoDTO> listarProdutos() {
        return produtoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public ProdutoDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
        return convertToDTO(produto);
    }
    
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
        
        if (!produto.getNome().equals(produtoDTO.getNome()) && 
            produtoRepository.existsByNome(produtoDTO.getNome())) {
            throw new BusinessException("Já existe um produto com este nome");
        }
        
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setPrecoUnitario(produtoDTO.getPrecoUnitario());
        
        Produto produtoAtualizado = produtoRepository.save(produto);
        return convertToDTO(produtoAtualizado);
    }
    
    public void removerProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
        
        produtoRepository.delete(produto);
    }
    
    public void atualizarEstoque(Long produtoId, Integer quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + produtoId));
        
        produto.setQuantidade(produto.getQuantidade() + quantidade);
        produtoRepository.save(produto);
    }
    
    public boolean verificarEstoqueDisponivel(Long produtoId, Integer quantidadeNecessaria) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + produtoId));
        
        return produto.getQuantidade() >= quantidadeNecessaria;
    }
    
    private ProdutoDTO convertToDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setQuantidade(produto.getQuantidade());
        dto.setPrecoUnitario(produto.getPrecoUnitario());
        dto.setCategoriaId(produto.getCategoria().getId());
        dto.setCategoriaNome(produto.getCategoria().getNome());
        return dto;
    }
}