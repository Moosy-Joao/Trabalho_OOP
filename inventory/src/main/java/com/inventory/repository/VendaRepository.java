package com.inventory.repository;

import com.inventory.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    
    List<Venda> findByClienteId(Long clienteId);
    
    List<Venda> findByProdutoId(Long produtoId);
    
    @Query("SELECT v FROM Venda v WHERE v.dataVenda BETWEEN :inicio AND :fim")
    List<Venda> findByDataVendaBetween(@Param("inicio") LocalDateTime inicio, 
                                       @Param("fim") LocalDateTime fim);
}