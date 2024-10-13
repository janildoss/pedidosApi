package com.api.pedidosApi.services;

import com.api.pedidosApi.Repositories.EstadoRepository;
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
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAll() ;
    }

    public Estado findById(Integer id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        return estado.orElseThrow(() -> new ObjectNotFoundException(
                "Estado não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
    }

    public Estado inserirEstado(Estado estado){
        estado.setId(null);
                return estadoRepository.save(estado);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteEstadoById(Integer id) {
        if (!estadoRepository.existsById(id)) {
            throw new ObjectNotFoundException("Estado não encontrada. Id: " + id);
        }
        estadoRepository.deleteById(id);
    }

    public Estado update(Integer id, Estado estado) {
        Optional<Estado> estadoExistente = estadoRepository.findById(id);

        if (estadoExistente.isPresent()) {
            Estado est = estadoExistente.get();
            est.setNome(estado.getNome());

        } else {
            throw new ObjectNotFoundException("Estado com id " + id + " não encontrada");
        }
    }
}
