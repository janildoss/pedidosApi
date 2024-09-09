package com.api.pedidosApi.services;

import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.models.Estado;
import com.api.pedidosApi.repositories.EstadoRepository;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;
    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Estado findOne(Integer id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        return estado.orElseThrow(() -> new ObjectNotFoundException(
                "Estado não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
    }

}
