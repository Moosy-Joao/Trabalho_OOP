package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.example.demo.Entities.Categoria;
import com.example.demo.repository.ICategoriaRepository;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
  @PostMapping
  public ResponseEntity<Categoria> registrarCategoria(@Valid @RequestBody Categoria categoria) {
    return ResponseEntity.status(201).body(categoriaRepository.save(categoria));
  }

  @GetMapping
  public ResponseEntity<List<Categoria>> listarCategorias() {
    return ResponseEntity.ok(categoriaRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Categoria> detalharCategoria(@PathVariable Long id) {
    return categoriaRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new CategoriaNotFoundException(id));
  }

  @GetMapping("/produtos")
  public ResponseEntity<List<Categoria>> listarCategoriasComProdutos() {
    return ResponseEntity.ok(categoriaRepository.findAll());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id,
      @Valid @RequestBody Categoria categoriaAtualizada) {
    return categoriaRepository.findById(id).map(categoria -> {
      categoria.setNome(categoriaAtualizada.getNome());
      categoria.setDescricao(categoriaAtualizada.getDescricao());
      return ResponseEntity.ok(categoriaRepository.save(categoria));
    }).orElseThrow(() -> new CategoriaNotFoundException(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removerCategoria(@PathVariable Long id) {
    if (categoriaRepository.existsById(id)) {
      categoriaRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
    throw new CategoriaNotFoundException(id);
  }

  // Add this class to handle Categoria not found exception
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(Long id) {
      super("Categoria not found with id: " + id);
    }
  }

  private final ICategoriaRepository categoriaRepository;

  public CategoriaController(ICategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }
  // Removed duplicate methods to avoid compilation errors.
}
