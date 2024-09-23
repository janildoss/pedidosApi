package com.api.pedidosApi.controllers;

import com.api.pedidosApi.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/categorias")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produto = produtoService.findAll();
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Produto> findCategoriaId(@PathVariable Integer id) {
        Produto produto =  produtoService.findById(id);
        return ResponseEntity.ok().body(produto );
    }

    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody Produto produto ) {
        Categoria cat = produtoService.inserirProduto(produto);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        produtoService.deleteProdutoById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.update(id, produto) ;
        return ResponseEntity.ok(produtoAtualizado);
    }
}