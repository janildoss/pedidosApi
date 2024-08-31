package com.api.pedidosApi.controllers;


import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.services.CategoriaService;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
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

        if (categoria.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Categoria> findCategoriaId(@PathVariable Integer id) {
        Categoria obj =  categoriaService.findOne(id);
        return ResponseEntity.ok().body(obj );
    }

    @PostMapping
    public ResponseEntity<Categoria> insertCategoria(@RequestBody Categoria categoria ) {
        Categoria cat = categoriaService.insert(categoria);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

  @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriaId(@PathVariable Integer id) {
        boolean cat = categoriaService.deleteCategoriaIdById(id);
        if (!cat) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        try {
            Categoria categoriaAtualizada = categoriaService.update(id, categoria);
            return ResponseEntity.ok(categoriaAtualizada);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
