package com.api.pedidosApi.services;

import com.api.pedidosApi.Repositories.PagamentoRepository;
import com.api.pedidosApi.Repositories.PedidoRepository;
import com.api.pedidosApi.models.Pagamento;
import com.api.pedidosApi.models.Pedido;
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
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pagamento> findAll(){
        return pagamentoRepository.findAll();
    }

    public Pagamento findById(Integer id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        return pagamento.orElseThrow(() -> new ObjectNotFoundException(
                "Pagamento não encontrada! Id: " + id + ", Tipo: " + Pagamento.class.getName()));
    }

    public Pagamento inserirPagamento(Pagamento pagamento) {
        pagamento.setId(null);
        // Salvar pagamento
        Pagamento novoPagamento = pagamentoRepository.save(pagamento);

        // Obter pedido vinculado
        Pedido pedido = novoPagamento.getPedido(); // supondo que pagamento tem getPedido()

        if (pedido.getEstadoPagamento().getCod() == 3) {
            throw new IllegalStateException("Pedido cancelado. Não é possível registrar pagamento.");
        }

        // Atualiza automaticamente o estado do pedido
       // int parcelasPagas = pedido.getPagamentos().size(); // já deve incluir o novo pagamento
       // int parcelasTotais = pedido.getNumParcela();

       // if (parcelasPagas >= parcelasTotais) {  pedido.setQuitado();  } else {  pedido.setPendente();        }

        pedidoRepository.save(pedido);

        return novoPagamento;
    }

    //Ao inserir pagamento verificar se e a ultima parcela se im quitar o pedido
    /*public Pagamento InsertPagamento (Pagamento pagamento){
        pagamento.setId(null);
        //return pagamentoRepository.save(pagamento);
        // Salvar o pagamento
        Pagamento pagtoSalvo = pagamentoRepository.save(pagamento);

        // Buscar o pedido relacionado
        Pedido pedido = pedidoRepository.findById(pagamento.getPedido().getId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Exemplo: se essa for a última parcela, marcar como QUITADO
        if (isUltimaParcela(pedido, pagamento)) {
            pedido.setQuitado(); // método da entidade Pedido
            pedidoRepository.save(pedido);
        }

        return pagtoSalvo;
    }

    private boolean isUltimaParcela(Pedido pedido, Pagamento pagamento) {
        // Verifica se é a última parcela
        return pagamento.getParcelaPagto().equals(pedido.getNumParcela());
    }*/


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
            //pagamento.setIdPedido(pagamentoAtualizado.getIdPedido());
            pagamento.setParcelaPagto(pagamentoAtualizado.getParcelaPagto());
            pagamento.setTipoRecebimento(pagamentoAtualizado.getTipoRecebimento());
            return pagamentoRepository.save(pagamentoAtualizado);
        } else {
            throw new ObjectNotFoundException("Categoria com id " + id + " não encontrada portanto não pode ser atualizada!");
        }
    }

}
