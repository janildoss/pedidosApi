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
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    private PagamentoRepository pagamentoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }


    public Pedido inserirPedido(Pedido pedido){
        pedido.setId(null);
        pedido.setPendente();
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

       Pagamento pagamento = pagamentoRepository.findById(id)
               .orElseThrow(() -> new ObjectNotFoundException( " não foi encontrado, pagamento para este pedido!"));

      Integer maxParcelaPagto = Optional.ofNullable(pagamentoRepository.findMaxParcelaPagtoByPedidoId(id)).orElse(0);

       if (pedidoAtualizado.isPendente() && pedidoAtualizado.isAvista()  && maxParcelaPagto != 0) {
           pedido.setQuitado();;
       }else{
           pedido.setPendente();
       }
       //cartao a vista
       if (pedidoAtualizado.isPendente() && pedidoAtualizado.isCartaoAvista()  && maxParcelaPagto != 0) {
           pedido.setQuitado();
       }else{
           pedido.setPendente();
       }
       //cartao parcelado
       if (pedidoAtualizado.isPendente() && pedidoAtualizado.isCartaoAprazo() && (maxParcelaPagto == pedidoAtualizado.getNumParcela()))   {
           pedido.setQuitado() ;
       }else{
           pedido.setEstadoPagamento(pedidoAtualizado.getEstadoPagamento());
       }
       pedido.setTipoPagamento(pedidoAtualizado.getTipoPagamento());
       pedido.setTipoPagamento(pedidoAtualizado.getTipoPagamento());
       pedido.setNumParcela(pedidoAtualizado.getNumParcela());

       return pedidoRepository.save(pedido);
   }

}