package com.api.pedidosApi.controllers;

import com.api.pedidosApi.DTO.PedidoDTO;
import com.api.pedidosApi.DTO.PedidoPostDTO;
import com.api.pedidosApi.DTO.PedidoStatusDTO;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoDTO> findPedidoId(@PathVariable Integer id) {
        Pedido pedido = pedidoService.findById(id);
        PedidoDTO pedidoDTO = new PedidoDTO(pedido);
        return ResponseEntity.ok().body(pedidoDTO);
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoPostDTO dto) {
        Pedido novoPedido = pedidoService.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        Pedido pedidoAtualizado = pedidoService.atualizaDadosPedido(id, pedido) ;
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @PutMapping("/{id}/atualizar-estado")
    public ResponseEntity<PedidoStatusDTO> atualizarEstado(@PathVariable Integer id) {
        PedidoStatusDTO dto = pedidoService.atualizarEstadoPagamento(id);
        return ResponseEntity.ok(dto);
    }

}


