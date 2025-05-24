package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entities.Fornecedor;
import com.example.demo.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public ResponseEntity<Fornecedor> createFornecedor(@RequestBody Fornecedor fornecedor) {
        Fornecedor createdFornecedor = fornecedorService.createFornecedor(fornecedor);
        return new ResponseEntity<>(createdFornecedor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> getAllFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.getAllFornecedores();
        return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable Long id) {
        return fornecedorService.getFornecedorById(id)
                .map(fornecedor -> new ResponseEntity<>(fornecedor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        try {
            Fornecedor updatedFornecedor = fornecedorService.updateFornecedor(id, fornecedor);
            return new ResponseEntity<>(updatedFornecedor, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
        fornecedorService.deleteFornecedor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
