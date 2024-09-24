package com.api.pedidosApi.services;

import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria inserirCategoria(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void deleteCategoriaById(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ObjectNotFoundException("Categoria não encontrada. Id: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    public Categoria updateCategoria(Integer id, Categoria categoriaAtualizada) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);

        if (categoriaExistente.isPresent()) {
            Categoria categoria = categoriaExistente.get();
            categoria.setNome(categoriaAtualizada.getNome());
            return categoriaRepository.save(categoria);
        } else {
            throw new ObjectNotFoundException("Categoria com id " + id + " não encontrada portanto não pode ser atualizada!");
        }
    }

}