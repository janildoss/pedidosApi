package com.api.pedidosApi.services;

import com.api.pedidosApi.Repositories.TipoPagamentoRepository;
import com.api.pedidosApi.models.TipoPagamento;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPagamentoService {
    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    public List<TipoPagamento> findAll() {
        return tipoPagamentoRepository.findAll();
    }

    public TipoPagamento findById(Integer id) {
        Optional<TipoPagamento> tipoPagamento = tipoPagamentoRepository.findById(id);
        return tipoPagamento.orElseThrow(() -> new ObjectNotFoundException(
                "TipoPagamento não encontrado! Id: " + id + ", Tipo: " + TipoPagamento.class.getName()));
    }

    public TipoPagamento inserirTipoPagamento(TipoPagamento tipoPagamento){
        tipoPagamento.setId(null);
        return tipoPagamentoRepository.save(tipoPagamento);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteTipoPagamentoById(Integer id) {
        if (!tipoPagamentoRepository.existsById(id)) {
            throw new ObjectNotFoundException("Tipo Pagamento não encontrado. Id: " + id);
        }
        tipoPagamentoRepository.deleteById(id);
    }

    public TipoPagamento updateTipoPagamento(Integer id, TipoPagamento tipoPagamento) {
        Optional<TipoPagamento> tipoPagamentoExistente = tipoPagamentoRepository.findById(id);

        if (tipoPagamentoExistente.isPresent()) {
            TipoPagamento tpPagamento = tipoPagamentoExistente.get();
            tpPagamento.setDescricao(tipoPagamento.getDescricao());

            return tipoPagamentoRepository.save(tpPagamento);
        } else {
            throw new ObjectNotFoundException("TipoPagamento com id " + id + " não encontrado portanto não pode ser atualizada!");
        }
    }

}