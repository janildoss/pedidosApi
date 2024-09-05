package com.api.pedidosApi.controllers;

import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.models.Produto;
import com.api.pedidosApi.services.ProdutoService;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {
   @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produto = produtoService.findAll();

        if (produto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Produto> findProdutoId(@PathVariable Integer id) {
        Produto obj =  produtoService.findOne(id);
        return ResponseEntity.ok().body(obj );
    }

    @PostMapping
    public ResponseEntity<Produto> insertProduto(@RequestBody Produto produto ) {
        Produto prod = produtoService.insert(produto);
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        try {
            Produto produtoAtualizado = produtoService.update(id, produto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Integer id) {
        boolean cat = produtoService.deleteProdutoById(id);
        if (!cat) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
