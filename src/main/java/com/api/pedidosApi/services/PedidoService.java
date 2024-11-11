package com.api.pedidosApi.services;

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
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    private PagamentoRepository pagamentoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return Pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }


    public Pedido inserirPedido(Pedido pedido){
        pedido.setId(null);
        pedido.setEstadoPagamento(1);

        return pedidoRepository.save(pedido);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deletePedidoById(Integer id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ObjectNotFoundException("Pedido não encontrado. Id: " + id);
        }
        pedidoRepository.deleteById(id);
    }

     public Pedido updatePedido(Integer id, Pedido pedidoAtualizado) {

       Pedido pedido = pedidoRepository.findById(id)
               .orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado, portanto não pode ser atualizado!"));

       Pagamento pagamento = PagamentoRepository.findById(id)
               .orElseThrow(() -> new ObjectNotFoundException( " não foi encontrado, ppagamento para este pedido!"));

       Integer maxParcela = PagamentoRepository.findMaxParcela(id);

       // EstadoPagamento: 1-Pendente, 2-Quitado, 3-Cancelado
       // TipoPagamento:1-A vista, 2-CartaoAvista e 3-CartaoParcelado
       if (pedidoAtualizado.getEstadoPagamento() == 1 && pedidoAtualizado.getTipoPagamento() == 1 && pagamento.getParcelaPagto() != null) {
           pedido.setEstadoPagamento(2);
       }else{
           pedido.setEstadoPagamento(pedidoAtualizado.getEstadoPagamento());
       }
       //cartao a vista
       if (pedidoAtualizado.getEstadoPagamento() == 1 && pedidoAtualizado.getTipoPagamento() == 2 && pagamento.getParcelaPagto() != null) {
           pedido.setEstadoPagamento(2);
       }else{
           pedido.setEstadoPagamento(pedidoAtualizado.getEstadoPagamento());
       }
       //cartao parcelado
       if (pedidoAtualizado.getEstadoPagamento() == 1 && pedidoAtualizado.getTipoPagamento() == 3 && (maxParcela <  pedidoAtualizado.getNumParcela())  ) {
           pedido.setEstadoPagamento(2);
       }else{
           pedido.setEstadoPagamento(pedidoAtualizado.getEstadoPagamento());
       }

       pedido.setTipoPagamento(pedidoAtualizado.getTipoPagamento());
       pedido.setTipoPagamento(pedidoAtualizado.getTipoPagamento());
       pedido.setNumParcela(pedidoAtualizado.getNumParcela());

       return pedidoRepository.save(pedido);
   }

}