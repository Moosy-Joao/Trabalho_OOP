package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import com.example.demo.Entities.Venda;
import com.example.demo.repository.IVendaRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    private final IVendaRepository vendaRepository;

    public VendaController(IVendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @PostMapping
    public ResponseEntity<Venda> registrarVenda(@RequestBody Venda venda) {
        venda.setDataVenda(LocalDateTime.now());
        venda.setValorTotal(calcularValorTotal(venda));
        return ResponseEntity.status(201).body(vendaRepository.save(venda));
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listarVendas() {
        return ResponseEntity.ok(vendaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> detalharVenda(@PathVariable Long id) {
        return vendaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerVenda(@PathVariable Long id) {
        if (vendaRepository.existsById(id)) {
            vendaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private BigDecimal calcularValorTotal(Venda venda) {
        BigDecimal precoUnitario = new BigDecimal("10.00"); // Simulação
        return precoUnitario.multiply(BigDecimal.valueOf(venda.getQuantidade()));
    }
}
