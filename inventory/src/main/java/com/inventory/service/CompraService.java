package com.inventory.service;

import com.inventory.dto.CompraDTO;
import com.inventory.entity.Compra;
import com.inventory.entity.Fornecedor;
import com.inventory.entity.Produto;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.repository.CompraRepository;
import com.inventory.repository.FornecedorRepository;
import com.inventory.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompraService {
    
    private final CompraRepository compraRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;
    
    @Autowired
    public CompraService(CompraRepository compraRepository, FornecedorRepository fornecedorRepository, 
                        ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.compraRepository = compraRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }
    
    public CompraDTO registrarCompra(CompraDTO compraDTO) {
        Fornecedor fornecedor = fornecedorRepository.findById(compraDTO.getFornecedorId())
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com ID: " + compraDTO.getFornecedorId()));
        
        Produto produto = produtoRepository.findById(compraDTO.getProdutoId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + compraDTO.getProdutoId()));
        
        BigDecimal valorTotal = produto.getPrecoUnitario().multiply(BigDecimal.valueOf(compraDTO.getQuantidade()));
        
        Compra compra = new Compra();
        compra.setFornecedor(fornecedor);
        compra.setProduto(produto);
        compra.setQuantidade(compraDTO.getQuantidade());
        compra.setValorTotal(valorTotal);
        
        Compra compraSalva = compraRepository.save(compra);
        
        // Atualizar estoque (aumentar)
        produtoService.atualizarEstoque(compraDTO.getProdutoId(), compraDTO.getQuantidade());
        
        return convertToDTO(compraSalva);
    }
    
    @Transactional(readOnly = true)
    public List<CompraDTO> listarCompras() {
        return compraRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public CompraDTO buscarCompraPorId(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra não encontrada com ID: " + id));
        return convertToDTO(compra);
    }
    
    @Transactional(readOnly = true)
    public List<CompraDTO> listarComprasPorFornecedor(Long fornecedorId) {
        return compraRepository.findByFornecedorId(fornecedorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<CompraDTO> listarComprasPorProduto(Long produtoId) {
        return compraRepository.findByProdutoId(produtoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public CompraDTO atualizarCompra(Long id, CompraDTO compraDTO) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra não encontrada com ID: " + id));
        
        // Restaurar estoque anterior
        produtoService.atualizarEstoque(compra.getProduto().getId(), -compra.getQuantidade());
        
        // Atualizar quantidade
        compra.setQuantidade(compraDTO.getQuantidade());
        
        // Recalcular valor total
        BigDecimal valorTotal = compra.getProduto().getPrecoUnitario()
                .multiply(BigDecimal.valueOf(compraDTO.getQuantidade()));
        compra.setValorTotal(valorTotal);
        
        Compra compraAtualizada = compraRepository.save(compra);
        
        // Aplicar novo estoque
        produtoService.atualizarEstoque(compra.getProduto().getId(), compraDTO.getQuantidade());
        
        return convertToDTO(compraAtualizada);
    }
    
    public void removerCompra(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra não encontrada com ID: " + id));
        
        // Restaurar estoque
        produtoService.atualizarEstoque(compra.getProduto().getId(), -compra.getQuantidade());
        
        compraRepository.delete(compra);
    }
    
    private CompraDTO convertToDTO(Compra compra) {
        CompraDTO dto = new CompraDTO();
        dto.setId(compra.getId());
        dto.setFornecedorId(compra.getFornecedor().getId());
        dto.setFornecedorNome(compra.getFornecedor().getNome());
        dto.setProdutoId(compra.getProduto().getId());
        dto.setProdutoNome(compra.getProduto().getNome());
        dto.setQuantidade(compra.getQuantidade());
        dto.setValorTotal(compra.getValorTotal());
        dto.setDataCompra(compra.getDataCompra());
        return dto;
    }
}