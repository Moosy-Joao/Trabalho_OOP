package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Compra;

@Repository
public interface IComprasRepository extends JpaRepository<Compra, Long> {
}
