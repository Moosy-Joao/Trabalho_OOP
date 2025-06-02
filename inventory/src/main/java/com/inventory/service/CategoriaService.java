package com.inventory.service;

import com.inventory.dto.CategoriaDTO;
import com.inventory.entity.Categoria;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.exception.BusinessException;
import com.inventory.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    
    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    
    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO) {
        if (categoriaRepository.existsByNome(categoriaDTO.getNome())) {
            throw new BusinessException("Já existe uma categoria com este nome");
        }
        
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());
        
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return convertToDTO(categoriaSalva);
    }
    
    @Transactional(readOnly = true)
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public CategoriaDTO buscarCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
        return convertToDTO(categoria);
    }
    
    @Transactional(readOnly = true)
    public List<Categoria> listarCategoriasComProdutos() {
        return categoriaRepository.findAllWithProdutos();
    }
    
    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
        
        if (!categoria.getNome().equals(categoriaDTO.getNome()) && 
            categoriaRepository.existsByNome(categoriaDTO.getNome())) {
            throw new BusinessException("Já existe uma categoria com este nome");
        }
        
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());
        
        Categoria categoriaAtualizada = categoriaRepository.save(categoria);
        return convertToDTO(categoriaAtualizada);
    }
    
    public void removerCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
        
        if (categoria.getProdutos() != null && !categoria.getProdutos().isEmpty()) {
            throw new BusinessException("Não é possível remover categoria que possui produtos associados");
        }
        
        categoriaRepository.delete(categoria);
    }
    
    private CategoriaDTO convertToDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        dto.setDescricao(categoria.getDescricao());
        return dto;
    }
}