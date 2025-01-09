package com.api.pedidosApi.controllers;

import com.api.pedidosApi.models.ItemPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/itempedidos")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoService itemPedidoService;

    @GetMapping
    public ResponseEntity<List<ItemPedido>> findAll() {
        List<ItemPedido> itemPedido = itemPedidoService.findAll();
        return new ResponseEntity<>(itemPedido, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ItemPedido> findItemPedidoById(@PathVariable Integer id) {
        ItemPedido itemPedido =  itemPedidoService.findById(id);
        return ResponseEntity.ok().body(itemPedido );
    }

    @PostMapping
    public ResponseEntity<ItemPedido> inserirItemPedido(@RequestBody ItemPedido itemPedido ) {
        ItemPedido novoItemPedido = itemPedidoService.inserirItemPedido(itemPedido);
        return new ResponseEntity<>(novoItemPedido, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemPedido(@PathVariable Integer id) {
        itemPedidoService.deleteItemPedidoById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> atualizarItemPedido(@PathVariable Integer id, @RequestBody ItemPedido itemPedido) {
        ItemPedido ItemPedidoAtualizado = itemPedidoService.atualizaItemPedido(id, itemPedido) ;
        return ResponseEntity.ok(ItemPedidoAtualizado);
    }

}
