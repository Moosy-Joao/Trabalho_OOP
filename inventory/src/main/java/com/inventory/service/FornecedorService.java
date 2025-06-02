package com.inventory.service;

import com.inventory.dto.FornecedorDTO;
import com.inventory.entity.Fornecedor;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.exception.BusinessException;
import com.inventory.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FornecedorService {
    
    private final FornecedorRepository fornecedorRepository;
    
    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }
    
    public FornecedorDTO criarFornecedor(FornecedorDTO fornecedorDTO) {
        if (fornecedorRepository.existsByCnpj(fornecedorDTO.getCnpj())) {
            throw new BusinessException("Já existe um fornecedor com este CNPJ");
        }
        
        if (fornecedorDTO.getEmail() != null && fornecedorRepository.existsByEmail(fornecedorDTO.getEmail())) {
            throw new BusinessException("Já existe um fornecedor com este email");
        }
        
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());
        fornecedor.setTelefone(fornecedorDTO.getTelefone());
        fornecedor.setEmail(fornecedorDTO.getEmail());
        
        Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);
        return convertToDTO(fornecedorSalvo);
    }
    
    @Transactional(readOnly = true)
    public List<FornecedorDTO> listarFornecedores() {
        return fornecedorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public FornecedorDTO buscarFornecedorPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com ID: " + id));
        return convertToDTO(fornecedor);
    }
    
    public FornecedorDTO atualizarFornecedor(Long id, FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com ID: " + id));
        
        if (!fornecedor.getCnpj().equals(fornecedorDTO.getCnpj()) && 
            fornecedorRepository.existsByCnpj(fornecedorDTO.getCnpj())) {
            throw new BusinessException("Já existe um fornecedor com este CNPJ");
        }
        
        if (fornecedorDTO.getEmail() != null && 
            !fornecedorDTO.getEmail().equals(fornecedor.getEmail()) && 
            fornecedorRepository.existsByEmail(fornecedorDTO.getEmail())) {
            throw new BusinessException("Já existe um fornecedor com este email");
        }
        
        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());
        fornecedor.setTelefone(fornecedorDTO.getTelefone());
        fornecedor.setEmail(fornecedorDTO.getEmail());
        
        Fornecedor fornecedorAtualizado = fornecedorRepository.save(fornecedor);
        return convertToDTO(fornecedorAtualizado);
    }
    
    public void removerFornecedor(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com ID: " + id));
        
        fornecedorRepository.delete(fornecedor);
    }
    
    private FornecedorDTO convertToDTO(Fornecedor fornecedor) {
        FornecedorDTO dto = new FornecedorDTO();
        dto.setId(fornecedor.getId());
        dto.setNome(fornecedor.getNome());
        dto.setCnpj(fornecedor.getCnpj());
        dto.setTelefone(fornecedor.getTelefone());
        dto.setEmail(fornecedor.getEmail());
        return dto;
    }
}