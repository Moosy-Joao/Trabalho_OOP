package com.inventory.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VendaDTO {
    
    private Long id;
    
    @NotNull(message = "Cliente ID é obrigatório")
    private Long clienteId;
    
    @NotNull(message = "Produto ID é obrigatório")
    private Long produtoId;
    
    private String produtoNome;
    
    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade deve ser maior que 0")
    private Integer quantidade;
    
    private BigDecimal valorTotal;
    
    private LocalDateTime dataVenda;

    // Construtores
    public VendaDTO() {
    }

    public VendaDTO(Long id, Long clienteId, Long produtoId, String produtoNome, Integer quantidade, 
                   BigDecimal valorTotal, LocalDateTime dataVenda) {
        this.id = id;
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }
}