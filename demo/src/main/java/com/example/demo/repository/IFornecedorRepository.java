package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Fornecedor;

@Repository
public interface IFornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
