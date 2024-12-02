package com.api.pedidosApi.services;

import com.api.pedidosApi.Repositories.TipoRecebimentoRepository;
import com.api.pedidosApi.models.TipoRecebimento;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoRecebimentoService {
    @Autowired
    private TipoRecebimentoRepository TipoRecebimentoRepository;

    public List<TipoRecebimento> findAll() {
        return TipoRecebimentoRepository.findAll();
    }

    public TipoRecebimento findById(Integer id) {
        Optional<TipoRecebimento> tipoRecebimento = TipoRecebimentoRepository.findById(id);
        return tipoRecebimento.orElseThrow(() -> new ObjectNotFoundException(
                "TipoRecebimento não encontrado! Id: " + id + ", Tipo: " + TipoRecebimento.class.getName()));
    }

    public TipoRecebimento inserirTipoRecebimento(TipoRecebimento tipoRecebimento){
        tipoRecebimento.setId(null);
        return TipoRecebimentoRepository.save(tipoRecebimento);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteTipoRecebimentoById(Integer id) {
        if (!TipoRecebimentoRepository.existsById(id)) {
            throw new ObjectNotFoundException("TipoRecebimento não encontrado. Id: " + id);
        }
        TipoRecebimentoRepository.deleteById(id);
    }

    public TipoRecebimento updateTipoRecebimento(Integer id, TipoRecebimento tipoRecebimento) {
        Optional<TipoRecebimento> TipoRecebimentoExistente = TipoRecebimentoRepository.findById(id);

        if (TipoRecebimentoExistente.isPresent()) {
            TipoRecebimento tpRecebimento = TipoRecebimentoExistente.get();
            tpRecebimento.setDescricao(tipoRecebimento.getDescricao());

            return TipoRecebimentoRepository.save(tpRecebimento);
        } else {
            throw new ObjectNotFoundException("TipoRecebimento com id " + id + " não encontrado portanto não pode ser atualizada!");
        }
    }

}
