package com.inventory.service;

import com.inventory.dto.VendaDTO;
import com.inventory.entity.Produto;
import com.inventory.entity.Venda;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.exception.BusinessException;
import com.inventory.repository.ProdutoRepository;
import com.inventory.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VendaService {
    
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;
    
    @Autowired
    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }
    
    public VendaDTO registrarVenda(VendaDTO vendaDTO) {
        Produto produto = produtoRepository.findById(vendaDTO.getProdutoId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + vendaDTO.getProdutoId()));
        
        if (!produtoService.verificarEstoqueDisponivel(vendaDTO.getProdutoId(), vendaDTO.getQuantidade())) {
            throw new BusinessException("Estoque insuficiente para realizar a venda");
        }
        
        BigDecimal valorTotal = produto.getPrecoUnitario().multiply(BigDecimal.valueOf(vendaDTO.getQuantidade()));
        
        Venda venda = new Venda();
        venda.setClienteId(vendaDTO.getClienteId());
        venda.setProduto(produto);
        venda.setQuantidade(vendaDTO.getQuantidade());
        venda.setValorTotal(valorTotal);
        
        Venda vendaSalva = vendaRepository.save(venda);
        
        // Atualizar estoque (diminuir)
        produtoService.atualizarEstoque(vendaDTO.getProdutoId(), -vendaDTO.getQuantidade());
        
        return convertToDTO(vendaSalva);
    }
    
    @Transactional(readOnly = true)
    public List<VendaDTO> listarVendas() {
        return vendaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public VendaDTO buscarVendaPorId(Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada com ID: " + id));
        return convertToDTO(venda);
    }
    
    public void removerVenda(Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada com ID: " + id));
        
        // Restaurar estoque
        produtoService.atualizarEstoque(venda.getProduto().getId(), venda.getQuantidade());
        
        vendaRepository.delete(venda);
    }
    
    private VendaDTO convertToDTO(Venda venda) {
        VendaDTO dto = new VendaDTO();
        dto.setId(venda.getId());
        dto.setClienteId(venda.getClienteId());
        dto.setProdutoId(venda.getProduto().getId());
        dto.setProdutoNome(venda.getProduto().getNome());
        dto.setQuantidade(venda.getQuantidade());
        dto.setValorTotal(venda.getValorTotal());
        dto.setDataVenda(venda.getDataVenda());
        return dto;
    }
}