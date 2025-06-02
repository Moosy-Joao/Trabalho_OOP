package com.inventory.controller;

import com.inventory.dto.CompraDTO;
import com.inventory.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {
    
    private final CompraService compraService;
    
    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }
    
    @PostMapping
    public ResponseEntity<CompraDTO> registrarCompra(@Valid @RequestBody CompraDTO compraDTO) {
        CompraDTO compraRegistrada = compraService.registrarCompra(compraDTO);
        return new ResponseEntity<>(compraRegistrada, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<CompraDTO>> listarCompras() {
        List<CompraDTO> compras = compraService.listarCompras();
        return ResponseEntity.ok(compras);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CompraDTO> buscarCompraPorId(@PathVariable Long id) {
        CompraDTO compra = compraService.buscarCompraPorId(id);
        return ResponseEntity.ok(compra);
    }
    
    @GetMapping("/fornecedor/{fornecedorId}")
    public ResponseEntity<List<CompraDTO>> listarComprasPorFornecedor(@PathVariable Long fornecedorId) {
        List<CompraDTO> compras = compraService.listarComprasPorFornecedor(fornecedorId);
        return ResponseEntity.ok(compras);
    }
    
    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<CompraDTO>> listarComprasPorProduto(@PathVariable Long produtoId) {
        List<CompraDTO> compras = compraService.listarComprasPorProduto(produtoId);
        return ResponseEntity.ok(compras);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CompraDTO> atualizarCompra(@PathVariable Long id, 
                                                    @Valid @RequestBody CompraDTO compraDTO) {
        CompraDTO compraAtualizada = compraService.atualizarCompra(id, compraDTO);
        return ResponseEntity.ok(compraAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCompra(@PathVariable Long id) {
        compraService.removerCompra(id);
        return ResponseEntity.noContent().build();
    }
}