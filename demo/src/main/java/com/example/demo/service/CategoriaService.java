package com.example.demo.service;

import com.example.demo.Entities.Categoria;
import com.example.demo.repository.ICategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final ICategoriaRepository icategoriaRepository;

    public CategoriaService(ICategoriaRepository icategoriaRepository) {
        this.icategoriaRepository = icategoriaRepository;
    }

    public Categoria createCategoria(Categoria categoria) {
        return icategoriaRepository.save(categoria);
    }

    public List<Categoria> getAllCategorias() {
        return icategoriaRepository.findAll();
    }

    public Optional<Categoria> getCategoriaById(Long id) {
        return icategoriaRepository.findById(id);
    }

    public Categoria updateCategoria(Long id, Categoria categoria) {
        return icategoriaRepository.findById(id)
                .map(existingCategoria -> {
                    existingCategoria.setNome(categoria.getNome());
                    return icategoriaRepository.save(existingCategoria);
                })
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public void deleteCategoria(Long id) {
        icategoriaRepository.deleteById(id);
    }
}
