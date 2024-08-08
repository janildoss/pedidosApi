package com.api.pedidosApi.controllers;

import com.api.pedidosApi.ErrorNotFounException.ErrosNotFoundException;
import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

   @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> categoria = categoriaService.FindAll();

        if (categoria.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findCategoriaId(@PathVariable Integer id) {
        Categoria categoria = (Categoria) categoriaService.FindOne(id);
       if (categoria == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> InsertCategoria(@RequestBody Categoria categoria ) {
        Categoria cat = categoriaService.Insert(categoria);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

  @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriaId(@PathVariable Integer id) {
        boolean cat = categoriaService.DeleteCategoriaIdById(id);
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
        } catch (ErrosNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
