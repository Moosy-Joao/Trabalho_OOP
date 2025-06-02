package com.inventory.repository;

import com.inventory.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByCategoriaId(Long categoriaId);
    
    @Query("SELECT p FROM Produto p WHERE p.quantidade < :quantidade")
    List<Produto> findByQuantidadeLessThan(@Param("quantidade") Integer quantidade);
    
    boolean existsByNome(String nome);
}