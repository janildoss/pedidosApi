package com.api.pedidosApi.services;

import com.api.pedidosApi.DTO.PedidoPostDTO;
import com.api.pedidosApi.DTO.PedidoStatusDTO;
import com.api.pedidosApi.Repositories.ClienteRepository;
import com.api.pedidosApi.Repositories.EnderecoRepository;
import com.api.pedidosApi.Repositories.PagamentoRepository;
import com.api.pedidosApi.Repositories.PedidoRepository;
import com.api.pedidosApi.models.Cliente;
import com.api.pedidosApi.models.Endereco;
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
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

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
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Endereco endereco = enderecoRepository.findById(pedido.getEnderecoDeEntrega().getId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        pedido.setPendente();
        pedido.setCliente(cliente);
        pedido.setEnderecoDeEntrega(endereco);

        return pedidoRepository.save(pedido);
    }

   /* public Pedido fromDTO(PedidoPostDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Endereco endereco = enderecoRepository.findById(dto.getEnderecoDeEntregaId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setDataPedido(dto.getDataPedido());
        pedido.setEstadoPagamento(EstadoPagamento.toEnum(dto.getEstadoPagamento().getCod()));
        pedido.setTipoPagamento(dto.getTipoPagamento());
        pedido.setNumParcela(dto.getNumParcela());
        pedido.setCliente(cliente);
        pedido.setEnderecoDeEntrega(endereco);

        return pedido;
    }*/

    public Pedido criarPedido(PedidoPostDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Endereco endereco = enderecoRepository.findById(dto.getEnderecoDeEntregaId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setDataPedido(dto.getDataPedido());
        pedido.setPendente();    //EstadoPagamento
        pedido.setTipoPagamento(dto.getTipoPagamento());
        pedido.setNumParcela(dto.getNumParcela());
        pedido.setCliente(cliente);
        pedido.setEnderecoDeEntrega(endereco);
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

    @Transactional
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

          Pedido pedidoExistente = pedidoRepository.findById(id)
               .orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado, portanto não pode ser atualizado!"));

           return pedidoRepository.save(pedidoExistente);
    }

    public Pedido atualizaDadosPedido(Integer id, Pedido pedidoAtualizado) {

        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido com id " + id + " não encontrado!"));

        // Atualiza apenas os campos necessários
        if (pedidoAtualizado.getTipoPagamento() != null) {
            pedidoExistente.setTipoPagamento(pedidoAtualizado.getTipoPagamento());
        }
        if (pedidoAtualizado.getNumParcela() != null) {
            pedidoExistente.setNumParcela(pedidoAtualizado.getNumParcela());
        }
        return pedidoRepository.save(pedidoExistente);
    }

    public PedidoStatusDTO atualizarEstadoPagamento(Integer pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + pedidoId));

        PedidoStatusDTO dto = new PedidoStatusDTO();
        dto.setPedidoId(pedidoId);

        if (pedido.getEstadoPagamento().getCod() == 3) {
            dto.setStatusAtual("CANCELADO");
            dto.setMensagem("Pedido está cancelado. Nenhuma atualização foi realizada.");
            return dto;
        }
        pedidoRepository.save(pedido);
        return dto;
    }

}

//}
