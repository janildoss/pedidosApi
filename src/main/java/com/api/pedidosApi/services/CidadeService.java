package com.api.pedidosApi.services;

import com.api.pedidosApi.models.Cidade;
import com.api.pedidosApi.models.Estado;
import com.api.pedidosApi.repositories.CidadeRepository;
import com.api.pedidosApi.repositories.EstadoRepository;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    public Cidade findOne(Integer id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        return cidade.orElseThrow(() -> new ObjectNotFoundException(
                "Cidade não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
    }

}
