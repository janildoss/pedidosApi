package com.api.pedidosApi.services;


import com.api.pedidosApi.Repositories.CidadeRepository;
import com.api.pedidosApi.models.Cidade;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findAll() {
        return cidadeRepository.findAll() ;
    }

    public Cidade findById(Integer id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        return cidade.orElseThrow(() -> new ObjectNotFoundException(
                "Cidade não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
    }

    public Cidade inserirCidade(Cidade cidade){
        cidade.setId(null);
        return cidadeRepository.save(cidade);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteCidadeById(Integer id) {
        if (!cidadeRepository.existsById(id)) {
            throw new ObjectNotFoundException("Cidade não encontrada. Id: " + id);
        }
        cidadeRepository.deleteById(id);
    }

    public Cidade update(Integer id, Cidade cidade) {
        Optional<Cidade> cidadeExistente = cidadeRepository.findById(id);

        if (cidadeExistente.isPresent()) {
            Cidade cid = cidadeExistente.get();
            cid.setNome(cidade.getNome());
            return cidadeRepository.save(cidade);
        } else {
            throw new ObjectNotFoundException("Cidade com id " + id + " não encontrada");
        }
    }
}

