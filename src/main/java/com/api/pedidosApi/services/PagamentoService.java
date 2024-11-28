package com.api.pedidosApi.services;

import com.api.pedidosApi.Repositories.PagamentoRepository;
import com.api.pedidosApi.models.Pagamento;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> findAll(){
        return pagamentoRepository.findAll();
    }

    public Pagamento findById(Integer id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        return pagamento.orElseThrow(() -> new ObjectNotFoundException(
                "Pagamento não encontrada! Id: " + id + ", Tipo: " + Pagamento.class.getName()));
    }

    public Pagamento InsertPagamento (Pagamento pagamento){
        pagamento.setId(null);
        return pagamentoRepository.save(pagamento);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deletePagamento(Integer id){
        if (!pagamentoRepository.existsById(id)) {
            throw new ObjectNotFoundException("Pagamento não encontrada. Id: " + id);
        }
        pagamentoRepository.deleteById(id);
    }

     public Pagamento updatePagamento(Integer id, Pagamento pagamentoAtualizado) {
        Optional<Pagamento> pagamentoExistente = pagamentoRepository.findById(id);

        if (pagamentoExistente.isPresent()) {
            Pagamento pagamento = pagamentoExistente.get();
            pagamento.setValor(pagamentoAtualizado.getValor() );
            pagamento.setDataPagto(pagamentoAtualizado.getDataPagto());
            pagamento.setIdPedido(pagamentoAtualizado.getIdPedido());
            pagamento.setParcelaPagto(pagamentoAtualizado.getParcelaPagto());
            pagamento.setTipoRecebimento(pagamentoAtualizado.getTipoRecebimento());
            return pagamentoRepository.save(pagamentoAtualizado);
        } else {
            throw new ObjectNotFoundException("Categoria com id " + id + " não encontrada portanto não pode ser atualizada!");
        }
    }

}
