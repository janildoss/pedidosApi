package com.api.pedidosApi.services;

import com.api.pedidosApi.Repositories.ItemPedidoRepository;
import com.api.pedidosApi.models.ItemPedido;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> findAll() {
        return itemPedidoRepository.findAll();
    }

    public ItemPedido findById(Integer id) {
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
        return itemPedido.orElseThrow(() -> new ObjectNotFoundException(
                "Item de Pedido não encontrado! Id: " + id + ", Tipo: " + ItemPedido.class.getName()));
    }

    public ItemPedido inserirItemPedido(ItemPedido ItemPedido){
        ItemPedido.setId(null);
        return itemPedidoRepository.save(ItemPedido);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteItemPedidoById(Integer id) {
        if (!itemPedidoRepository.existsById(id)) {
            throw new ObjectNotFoundException("Item de Pedido não encontrado. Id: " + id);
        }
        itemPedidoRepository.deleteById(id);
    }

    public ItemPedido atualizaItemPedido(Integer id, ItemPedido itempedidoAtualizado) {
        ItemPedido iTpedidoExistente = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Item do Pedido com id " + id + " não encontrado!"));

        iTpedidoExistente.setQuantidade(itempedidoAtualizado.getQuantidade());
        iTpedidoExistente.setPreco(itempedidoAtualizado.getPreco());
        iTpedidoExistente.setDesconto(itempedidoAtualizado.getDesconto());

        return itemPedidoRepository.save(iTpedidoExistente);
    }

}
