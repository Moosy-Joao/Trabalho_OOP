package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
  private Long id;
  private String nome;
  private String descricao;

  // Construtor
  public Categoria(Long id, String nome, String descricao) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
  }

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Override
  public String toString() {
    return "Categoria{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", descricao='" + descricao + '\'' +
        '}';
  }
}
