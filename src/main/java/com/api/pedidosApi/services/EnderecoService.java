package com.api.pedidosApi.services;

import com.api.pedidosApi.models.Cidade;
import com.api.pedidosApi.models.Endereco;
import com.api.pedidosApi.repositories.CidadeRepository;
import com.api.pedidosApi.repositories.EnderecoRepository;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Endereco findOne(Integer id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.orElseThrow(() -> new ObjectNotFoundException(
                "Endereco não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
    }

}
