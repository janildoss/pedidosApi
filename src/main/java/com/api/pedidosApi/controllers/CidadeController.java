package com.api.pedidosApi.controllers;

import com.api.pedidosApi.models.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll() {
        List<Cidade> cidade = cidadeService.findAll();
        return new ResponseEntity<>(cidade, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Cidade> findCidadeById(@PathVariable Integer id) {
        Cidade cidade =  cidadeService.findById(id);
        return ResponseEntity.ok().body(cidade);
    }

    @PostMapping
    public ResponseEntity<Cidade> insertCidade(@RequestBody Cidade cidade ) {
        Cidade  cid = cidadeService.inserirCidade(cidade);
        return new ResponseEntity<>(cid, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCidade(@PathVariable Integer id) {
        cidadeService.deleteCidadeById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> atualizarCidade(@PathVariable Integer id, @RequestBody Cidade cidade) {
        Cidade cidadeAtualizada = cidadeService.update(id, cidade) ;
        return ResponseEntity.ok(cidadeAtualizada);
    }

}