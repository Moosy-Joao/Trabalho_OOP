package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Ensure the Categoria class exists in the correct package
import com.example.demo.Entities.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
