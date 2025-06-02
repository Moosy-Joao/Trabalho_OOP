package com.inventory.controller;

import com.inventory.dto.CategoriaDTO;
import com.inventory.entity.Categoria;
import com.inventory.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "API para gerenciamento de categorias de produtos")
public class CategoriaController {
    
    private final CategoriaService categoriaService;
    
    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    
    @PostMapping
    @Operation(summary = "Criar uma nova categoria", description = "Cria uma nova categoria de produtos no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso",
                content = @Content(schema = @Schema(implementation = CategoriaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "409", description = "Categoria com mesmo nome já existe")
    })
    public ResponseEntity<CategoriaDTO> criarCategoria(
            @Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaCriada = categoriaService.criarCategoria(categoriaDTO);
        return new ResponseEntity<>(categoriaCriada, HttpStatus.CREATED);
    }
    
    @GetMapping
    @Operation(summary = "Listar todas as categorias", description = "Retorna uma lista de todas as categorias cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<CategoriaDTO> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria específica pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<CategoriaDTO> buscarCategoriaPorId(
            @Parameter(description = "ID da categoria", required = true)
            @PathVariable Long id) {
        CategoriaDTO categoria = categoriaService.buscarCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }
    
    @GetMapping("/produtos")
    @Operation(summary = "Listar categorias com produtos", description = "Retorna todas as categorias com seus produtos associados")
    @ApiResponse(responseCode = "200", description = "Lista de categorias com produtos retornada com sucesso")
    public ResponseEntity<List<Categoria>> listarCategoriasComProdutos() {
        List<Categoria> categorias = categoriaService.listarCategoriasComProdutos();
        return ResponseEntity.ok(categorias);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria", description = "Atualiza os dados de uma categoria existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
        @ApiResponse(responseCode = "409", description = "Categoria com mesmo nome já existe")
    })
    public ResponseEntity<CategoriaDTO> atualizarCategoria(
            @Parameter(description = "ID da categoria", required = true)
            @PathVariable Long id, 
            @Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaAtualizada = categoriaService.atualizarCategoria(id, categoriaDTO);
        return ResponseEntity.ok(categoriaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover categoria", description = "Remove uma categoria do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoria removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
        @ApiResponse(responseCode = "400", description = "Não é possível remover categoria com produtos associados")
    })
    public ResponseEntity<Void> removerCategoria(
            @Parameter(description = "ID da categoria", required = true)
            @PathVariable Long id) {
        categoriaService.removerCategoria(id);
        return ResponseEntity.noContent().build();
    }
}