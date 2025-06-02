package com.inventory.controller;

import com.inventory.dto.VendaDTO;
import com.inventory.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {
    
    private final VendaService vendaService;
    
    @Autowired
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }
    
    @PostMapping
    public ResponseEntity<VendaDTO> registrarVenda(@Valid @RequestBody VendaDTO vendaDTO) {
        VendaDTO vendaRegistrada = vendaService.registrarVenda(vendaDTO);
        return new ResponseEntity<>(vendaRegistrada, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<VendaDTO>> listarVendas() {
        List<VendaDTO> vendas = vendaService.listarVendas();
        return ResponseEntity.ok(vendas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscarVendaPorId(@PathVariable Long id) {
        VendaDTO venda = vendaService.buscarVendaPorId(id);
        return ResponseEntity.ok(venda);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerVenda(@PathVariable Long id) {
        vendaService.removerVenda(id);
        return ResponseEntity.noContent().build();
    }
}