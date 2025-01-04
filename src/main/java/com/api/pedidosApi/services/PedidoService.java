package com.api.pedidosApi.services;

import com.api.pedidosApi.Repositories.PagamentoRepository;
import com.api.pedidosApi.Repositories.PedidoRepository;
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

    public Pedido atualizaPedidoIsQuitado(Integer id) {
        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado, portanto não pode ser atualizado!"));

              pedidoExistente.setQuitado();

             return pedidoRepository.save(pedidoExistente);
    }

    public  Pedido obterPedido(Integer id){
         Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado, portanto não pode ser atualizado!"));

        return pedidoExistente;
    }

    public Pedido atualizaPedidoIsCancelado(Pedido pedido) {

       pedido.setCancelado();
        return pedidoRepository.save(pedido);
    }

     public Pedido atualizaPedidoCartaoParcelado(Integer id) {
          //Pedido ped = obterPedido(id);

          Pedido pedidoExistente = pedidoRepository.findById(id)
               .orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado, portanto não pode ser atualizado!"));

           Integer maxParcelaPagto = Optional.ofNullable(pagamentoRepository.findMaxParcelaPagtoByPedidoId(id)).orElse(0);
           if (pedidoExistente.isPendente() && pedidoExistente.isCartaoAprazo() && (maxParcelaPagto.equals(pedidoExistente.getNumParcela()))) {
               pedidoExistente.setQuitado() ;
           }else{
               pedidoExistente.setPendente();
           }
           return pedidoRepository.save(pedidoExistente);
    }

    public Pedido atualizaDadosPedido(Integer id, Pedido pedidoAtualizado) {

        Pedido pedidoExistente = pedidoRepository.findById(pedidoAtualizado.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + pedidoAtualizado.getId() + " não encontrado!"));

        // Atualiza apenas os campos necessários
        if (pedidoAtualizado.getTipoPagamento() != null) {
            pedidoExistente.setTipoPagamento(pedidoAtualizado.getTipoPagamento());
        }
        if (pedidoAtualizado.getNumParcela() != null) {
            pedidoExistente.setNumParcela(pedidoAtualizado.getNumParcela());
        }

        return pedidoRepository.save(pedidoExistente);
    }

}
