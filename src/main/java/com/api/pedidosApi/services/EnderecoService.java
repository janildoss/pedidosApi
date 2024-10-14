package com.api.pedidosApi.services;

import com.api.pedidosApi.models.Endereco;
import com.api.pedidosApi.models.Estado;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll() ;
    }

    public Endereco findById(Integer id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.orElseThrow(() -> new ObjectNotFoundException(
                "Endereco não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
    }

    public Endereco inserirEndereco(Endereco endereco){
        endereco.setId(null);
        return enderecoRepository.save(endereco);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteEnderecoById(Integer id) {
        if (!enderecoRepository.existsById(id)) {
            throw new ObjectNotFoundException("Endereco não encontrada. Id: " + id);
        }
        enderecoRepository.deleteById(id);
    }

    public Endereco update(Integer id, Endereco endereco) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);

        if (enderecoExistente.isPresent()) {
            Estado end = enderecoExistente.get();
            end.setNome(Endereco.getNome());
            return enderecoRepository.save(end);
        } else {
            throw new ObjectNotFoundException("Endereco com id " + id + " não encontrada");
        }
    }
}

