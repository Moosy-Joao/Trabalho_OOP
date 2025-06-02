package com.inventory.repository;

import com.inventory.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    
    List<Compra> findByFornecedorId(Long fornecedorId);
    
    List<Compra> findByProdutoId(Long produtoId);
    
    @Query("SELECT c FROM Compra c WHERE c.dataCompra BETWEEN :inicio AND :fim")
    List<Compra> findByDataCompraBetween(@Param("inicio") LocalDateTime inicio, 
                                         @Param("fim") LocalDateTime fim);
}