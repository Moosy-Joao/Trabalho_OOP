package com.inventory.repository;

import com.inventory.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    
    boolean existsByCnpj(String cnpj);
    
    boolean existsByEmail(String email);
}