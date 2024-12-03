package com.api.pedidosApi.controllers;


import com.api.pedidosApi.models.TipoRecebimento;
import com.api.pedidosApi.services.TipoRecebimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/tiporecebimentos")
public class TipoRecebimentoController {
    @Autowired
    private TipoRecebimentoService tipoRecebimentoService;

    @GetMapping
    public ResponseEntity<List<TipoRecebimento>> findAll() {
        List<TipoRecebimento> tipoRecebimento = tipoRecebimentoService.findAll();
        return new ResponseEntity<>(tipoRecebimento, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<TipoRecebimento> findTipoRecebimentoById(@PathVariable Integer id) {
        TipoRecebimento tipoRecebimento =  tipoRecebimentoService.findById(id);
        return ResponseEntity.ok().body(tipoRecebimento );
    }

    @PostMapping
    public ResponseEntity<TipoRecebimento> insertRecebimento(@RequestBody TipoRecebimento tipoRecebimento ) {
        TipoRecebimento novoTipoRecebimento = tipoRecebimentoService.inserirTipoRecebimento(tipoRecebimento);
        return new ResponseEntity<>(novoTipoRecebimento, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRecebimentoById(@PathVariable Integer id) {
        tipoRecebimentoService.deleteTipoRecebimentoById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoRecebimento> atualizarTipoPagamento(@PathVariable Integer id, @RequestBody TipoRecebimento tipoRecebimento) {
        TipoRecebimento TipoRecebimentoAtualizado = tipoRecebimentoService.updateTipoRecebimento(id, tipoRecebimento)  ;
        return ResponseEntity.ok(TipoRecebimentoAtualizado);
    }
}
