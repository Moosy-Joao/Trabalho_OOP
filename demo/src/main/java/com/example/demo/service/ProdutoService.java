// package com.example.demo.service;

// import com.example.demo.entities.Produto;
// import com.example.demo.repository.IProdutoRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.Optional;

// @Service
// public class ProdutoService {

//     @Autowired
//     private IProdutoRepository produtoRepository;

//     public Produto adicionarProduto(Produto produto) {
//         return produtoRepository.save(produto);
//     }

//     public Optional<Produto> buscarProdutoPorId(Long id) {
//         return produtoRepository.findById(id);
//     }

//     public boolean verificarEstoqueDisponivel(Long produtoId, int quantidade) {
//         Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);
//         if (produtoOpt.isPresent()) {
//             Produto produto = produtoOpt.get();
//             return produto.getQuantidade() != null && produto.getQuantidade() >= quantidade;
//         }
//         return false;
//     }

//     public Produto atualizarQuantidade(Long produtoId, int novaQuantidade) {
//         Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);
//         if (produtoOpt.isPresent()) {
//             Produto produto = produtoOpt.get();
//             produto.setQuantidade(novaQuantidade);
//             return produtoRepository.save(produto);
//         }
//         return null;
//     }
// }
