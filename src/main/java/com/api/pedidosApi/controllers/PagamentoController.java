package com.api.pedidosApi.controllers;

import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.models.Pagamento;
import com.api.pedidosApi.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/pagamentos")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<Pagamento>> findAll() {
        List<Pagamento> pagamento = pagamentoService.findAll();
        return new ResponseEntity<>(pagamento, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Pagamento> findPagamentoId(@PathVariable Integer id) {
        Pagamento pagamento =  pagamentoService.findById(id);
        return ResponseEntity.ok().body(pagamento );
    }

    @PostMapping
    public ResponseEntity<Pagamento> insertPagamento(@RequestBody Pagamento pagamento ) {
        Pagamento novoPagamento = pagamentoService.InsertPagamento(pagamento);
        return new ResponseEntity<>(novoPagamento, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Integer id) {
        pagamentoService.deletePagamento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable Integer id, @RequestBody Pagamento pagamento) {
        Pagamento pagamentoAtualizado = pagamentoService.updatePagamento(id, pagamento) ;
        return ResponseEntity.ok(pagamentoAtualizado);
    }


}
