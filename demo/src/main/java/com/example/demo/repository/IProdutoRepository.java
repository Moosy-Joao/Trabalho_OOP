package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Produto;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Long> {
}
