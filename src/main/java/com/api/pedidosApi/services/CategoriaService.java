package com.api.pedidosApi.services;

import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.repositories.CategoriaRepository;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
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
      return obj.orElseThrow(() -> new ObjectNotFoundException(
              "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria Insert(Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public boolean DeleteCategoriaIdById(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ObjectNotFoundException("Categoria not found with ID: " + id);
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
            throw new ObjectNotFoundException("Categoria com id " + id + " não encontrada");
        }
    }

}
