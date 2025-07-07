package com.api.pedidosApi.controllers;

import com.api.pedidosApi.DTO.EnderecoDTO;
import com.api.pedidosApi.models.Cidade;
import com.api.pedidosApi.models.Cliente;
import com.api.pedidosApi.models.Endereco;
import com.api.pedidosApi.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> findAll() {
        List<Endereco> endereco = enderecoService.findAll();
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Endereco> findEnderecoById(@PathVariable Integer id) {
        Endereco endereco =  enderecoService.findById(id);
        return ResponseEntity.ok().body(endereco);
    }

    @PostMapping
    public ResponseEntity<Endereco> insertEndereco(@RequestBody EnderecoDTO dto) {
        Endereco endereco = enderecoService.fromDTO(dto);
        Endereco salvo = enderecoService.inserirEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
  /*  @PostMapping
    public ResponseEntity<Endereco> insertEndereco(@RequestBody Endereco endereco ) {
        Endereco end = enderecoService.inserirEndereco(endereco);
        return new ResponseEntity<>(end, HttpStatus.CREATED);
    }*/


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer id) {
        enderecoService.deleteEnderecoById(id);
        return ResponseEntity.noContent().build();
    }

   /* @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco endereco) {
        Endereco enderecoAtualizado = enderecoService.update(id, endereco) ;
        return ResponseEntity.ok(enderecoAtualizado);
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer id, @RequestBody EnderecoDTO dto) {
        Endereco atualizado = enderecoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }


}
