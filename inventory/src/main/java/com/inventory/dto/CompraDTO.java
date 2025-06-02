package com.inventory.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraDTO {
    
    private Long id;
    
    @NotNull(message = "Fornecedor ID é obrigatório")
    private Long fornecedorId;
    
    private String fornecedorNome;
    
    @NotNull(message = "Produto ID é obrigatório")
    private Long produtoId;
    
    private String produtoNome;
    
    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade deve ser maior que 0")
    private Integer quantidade;
    
    private BigDecimal valorTotal;
    
    private LocalDateTime dataCompra;

    // Construtores
    public CompraDTO() {
    }

    public CompraDTO(Long id, Long fornecedorId, String fornecedorNome, Long produtoId, String produtoNome, 
                    Integer quantidade, BigDecimal valorTotal, LocalDateTime dataCompra) {
        this.id = id;
        this.fornecedorId = fornecedorId;
        this.fornecedorNome = fornecedorNome;
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataCompra = dataCompra;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public String getFornecedorNome() {
        return fornecedorNome;
    }

    public void setFornecedorNome(String fornecedorNome) {
        this.fornecedorNome = fornecedorNome;
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

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }
}