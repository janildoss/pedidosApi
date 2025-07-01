package com.api.pedidosApi.controllers;

import com.api.pedidosApi.DTO.ClienteNewDTO;
import com.api.pedidosApi.models.Cliente;
import com.api.pedidosApi.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> cliente = clienteService.findAll();
         return new ResponseEntity<>(cliente, HttpStatus.OK);
   }

    @GetMapping(value="/{id}")
    public ResponseEntity<Cliente> findClienteId(@PathVariable Integer id) {
        Cliente cliente =  clienteService.findById(id);
        return ResponseEntity.ok().body(cliente );
    }
    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody ClienteNewDTO objDto) {
        Cliente obj = clienteService.fromDTO(objDto);
        obj = clienteService.inserirCliente(obj);

        // Montar manualmente a URI: http://localhost:8080/clientes/{id}
        URI uri = URI.create("/clientes/" + obj.getId());

        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Integer id) {
        clienteService.deleteClienteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.updateCliente(id, cliente) ;
        return ResponseEntity.ok(clienteAtualizado);
    }


}
