package com.api.pedidosApi.controllers;

import com.api.pedidosApi.models.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> findAll() {
        List<Estado> estado = estadoService.findAll();
        return new ResponseEntity<>(estado, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Estado> findEstadoById(@PathVariable Integer id) {
        Estado estado =  estadoService.findById(id);
        return ResponseEntity.ok().body(estado );
    }

    @PostMapping
    public ResponseEntity<Estado> insertEstado(@RequestBody Estado estado ) {
        Estado est = estadoService.inserirEstado(estado);
        return new ResponseEntity<>(est, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstado(@PathVariable Integer id) {
        estadoService.deleteEstadoById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizarEstado(@PathVariable Integer id, @RequestBody Estado estado) {
        Estado estadoAtualizado = estadoService.updateEstado(id, estado) ;
        return ResponseEntity.ok(estadoAtualizado);
    }

}