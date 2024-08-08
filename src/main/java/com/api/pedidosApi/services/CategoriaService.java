package com.api.pedidosApi.services;

import com.api.pedidosApi.ErrorNotFounException.ErrosNotFoundException;
import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> FindAll() {
       return categoriaRepository.findAll();
    }

     public Categoria FindOne(Integer id) {
       Optional<Categoria> obj = categoriaRepository.findById(id);
       return obj.orElseThrow(() -> new ErrosNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria Insert(Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public boolean DeleteCategoriaIdById(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ErrosNotFoundException("Categoria not found with ID: " + id);
        }
        categoriaRepository.deleteById(id);
        return true;
    }

    public Categoria update(Integer id, Categoria categoriaAtualizada) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);

        if (categoriaExistente.isPresent()) {
            Categoria categoria = categoriaExistente.get();
            categoria.setNome(categoriaAtualizada.getNome());
            return categoriaRepository.save(categoria);
        } else {
            throw new ErrosNotFoundException("Categoria com id " + id + " não encontrada");
        }
    }

}
