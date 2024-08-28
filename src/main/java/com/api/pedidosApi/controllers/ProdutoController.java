package com.api.pedidosApi.controllers;

import com.api.pedidosApi.models.Produto;
import com.api.pedidosApi.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {
   @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produto = produtoService.findAll();

        if (produto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }
}
