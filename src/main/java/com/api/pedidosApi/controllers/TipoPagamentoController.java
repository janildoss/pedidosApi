package com.api.pedidosApi.controllers;

import com.api.pedidosApi.models.TipoPagamento;
import com.api.pedidosApi.services.TipoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/tipopagamentos")
public class TipoPagamentoController {
    @Autowired
    private TipoPagamentoService tipoPagamentoService;

    @GetMapping
    public ResponseEntity<List<TipoPagamento>> findAll() {
        List<TipoPagamento> tipoPagamento = tipoPagamentoService.findAll();
        return new ResponseEntity<>(tipoPagamento, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<TipoPagamento> findTipoPagamentoById(@PathVariable Integer id) {
        TipoPagamento tipoPagamento =  tipoPagamentoService.findById(id);
        return ResponseEntity.ok().body(tipoPagamento );
    }

    @PostMapping
    public ResponseEntity<TipoPagamento> insertPagamento(@RequestBody TipoPagamento tipoPagamento ) {
        TipoPagamento novoTipoPagamento = tipoPagamentoService.inserirTipoPagamento(tipoPagamento);
        return new ResponseEntity<>(novoTipoPagamento, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamentoById(@PathVariable Integer id) {
        tipoPagamentoService.deleteTipoPagamentoById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoPagamento> atualizarTipoPagamento(@PathVariable Integer id, @RequestBody TipoPagamento tipoPagamento) {
        TipoPagamento TipoPagamentoAtualizado = tipoPagamentoService.updateTipoPagamento(id, tipoPagamento)  ;
        return ResponseEntity.ok(TipoPagamentoAtualizado);
    }
}
