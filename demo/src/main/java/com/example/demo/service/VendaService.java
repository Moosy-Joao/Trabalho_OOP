package com.example.demo.service;

import com.example.demo.Entities.Venda;
import com.example.demo.repository.IVendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final IVendaRepository ivendaRepository;

    public VendaService(IVendaRepository vendaRepository) {
        this.ivendaRepository = vendaRepository;
    }

    public Venda createVenda(Venda venda) {
        return ivendaRepository.save(venda);
    }

    public List<Venda> getAllVendas() {
        return ivendaRepository.findAll();
    }

    public Optional<Venda> getVendaById(Long id) {
        return ivendaRepository.findById(id);
    }

    public Venda updateVenda(Long id, Venda venda) {
        return ivendaRepository.findById(id)
                .map(existingVenda -> {
                    existingVenda.setDataVenda(venda.getDataVenda());
                    existingVenda.setClienteId(venda.getClienteId());
                    existingVenda.setId(venda.getId());
                    existingVenda.setValorTotal(venda.getValorTotal());
                    return ivendaRepository.save(existingVenda);
                })
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    public void deleteVenda(Long id) {
        ivendaRepository.deleteById(id);
    }
}
