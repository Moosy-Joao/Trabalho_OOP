package com.example.demo.service;

import com.example.demo.entities.Fornecedor;
import com.example.demo.repository.IFornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    @Autowired
    private IFornecedorRepository fornecedorRepository;

    public Fornecedor adicionarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }
}
