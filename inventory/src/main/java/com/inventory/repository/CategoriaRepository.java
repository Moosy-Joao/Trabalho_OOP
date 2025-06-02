package com.inventory.repository;

import com.inventory.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.produtos")
    List<Categoria> findAllWithProdutos();
    
    boolean existsByNome(String nome);
}