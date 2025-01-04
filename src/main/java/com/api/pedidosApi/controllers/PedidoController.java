package com.api.pedidosApi.controllers;

import com.api.pedidosApi.models.Pagamento;
import com.api.pedidosApi.models.Pedido;
import com.api.pedidosApi.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedido = pedidoService.findAll();
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Pedido> findPedidoId(@PathVariable Integer id) {
        Pedido pedido =  pedidoService.findById(id);
        return ResponseEntity.ok().body(pedido );
    }

    @PostMapping
    public ResponseEntity<Pedido> insertPedido(@RequestBody Pedido pedido ) {
        Pedido novoPedido = pedidoService.inserirPedido(pedido);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        Pedido pedidoAtualizado = pedidoService.atualizaDadosPedido(id, pedido) ;
        return ResponseEntity.ok(pedidoAtualizado);
    }

}
