package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entities.Fornecedor;
import com.example.demo.repository.IFornecedorRepository;

@Service
public class FornecedorService {

    private final IFornecedorRepository fornecedorRepository;

    public FornecedorService(IFornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public Fornecedor createFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> getFornecedorById(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor updateFornecedor(Long id, Fornecedor fornecedorDetails) {
        return fornecedorRepository.findById(id).map(fornecedor -> {
            fornecedor.setNome(fornecedorDetails.getNome());
            fornecedor.setCnpj(fornecedorDetails.getCnpj());
            fornecedor.setTelefone(fornecedorDetails.getTelefone());
            fornecedor.setEmail(fornecedorDetails.getEmail());
            return fornecedorRepository.save(fornecedor);
        }).orElseThrow(() -> new RuntimeException("Fornecedor not found with id " + id));
    }

    public void deleteFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
