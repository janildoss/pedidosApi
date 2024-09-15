
package com.api.pedidosApi.controllers;


import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categoria = categoriaService.findAll();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Categoria> findCategoriaId(@PathVariable Integer id) {
        Categoria categoria =  categoriaService.findById(id);
        return ResponseEntity.ok().body(categoria );
    }

    @PostMapping
    public ResponseEntity<Categoria> insertCategoria(@RequestBody Categoria categoria ) {
        Categoria cat = categoriaService.inserirCategoria(categoria);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id) {
        categoriaService.deleteCategoriaById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        Categoria categoriaAtualizada = categoriaService.updateCategoria(id, categoria) ;
        return ResponseEntity.ok(categoriaAtualizada);
    }
}