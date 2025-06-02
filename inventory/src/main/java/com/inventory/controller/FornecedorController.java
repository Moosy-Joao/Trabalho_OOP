package com.inventory.controller;

import com.inventory.dto.FornecedorDTO;
import com.inventory.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {
    
    private final FornecedorService fornecedorService;
    
    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }
    
    @PostMapping
    public ResponseEntity<FornecedorDTO> criarFornecedor(@Valid @RequestBody FornecedorDTO fornecedorDTO) {
        FornecedorDTO fornecedorCriado = fornecedorService.criarFornecedor(fornecedorDTO);
        return new ResponseEntity<>(fornecedorCriado, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> listarFornecedores() {
        List<FornecedorDTO> fornecedores = fornecedorService.listarFornecedores();
        return ResponseEntity.ok(fornecedores);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> buscarFornecedorPorId(@PathVariable Long id) {
        FornecedorDTO fornecedor = fornecedorService.buscarFornecedorPorId(id);
        return ResponseEntity.ok(fornecedor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDTO> atualizarFornecedor(@PathVariable Long id, 
                                                            @Valid @RequestBody FornecedorDTO fornecedorDTO) {
        FornecedorDTO fornecedorAtualizado = fornecedorService.atualizarFornecedor(id, fornecedorDTO);
        return ResponseEntity.ok(fornecedorAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerFornecedor(@PathVariable Long id) {
        fornecedorService.removerFornecedor(id);
        return ResponseEntity.noContent().build();
    }
}