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

    public Pedido atualizaPedidoIsQuitado(Integer id, Pedido pedido) {
        Pedido ped = pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado, portanto não pode ser atualizado!"));

             ped.setQuitado();

             return pedidoRepository.save(ped);
    }

    public Pedido atualizaPedidoIsCancelado(Integer id, Pedido pedido) {
        Pedido ped = pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado, portanto não pode ser atualizado!"));

        ped.setCancelado();

        return pedidoRepository.save(ped);
    }

     public Pedido atualizaPedidoCartaoParcelado(Integer id, Pedido pedido) {

          Pedido ped = pedidoRepository.findById(id)
               .orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado, portanto não pode ser atualizado!"));

           Integer maxParcelaPagto = Optional.ofNullable(pagamentoRepository.findMaxParcelaPagtoByPedidoId(id)).orElse(0);
           if (ped.isPendente() && ped.isCartaoAprazo() && (maxParcelaPagto.equals(ped.getNumParcela()))) {
              ped.setQuitado() ;
           }else{
              ped.setPendente();
           }
           return pedidoRepository.save(ped);
    }

    public Pedido atualizaDadosPedido(Integer id, Pedido pedido) {

        Pedido ped = pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado, portanto não pode ser atualizado!"));

        ped.setTipoPagamento(pedido.getTipoPagamento());
        ped.setNumParcela(pedido.getNumParcela());
        return pedidoRepository.save(ped);
    }



}
