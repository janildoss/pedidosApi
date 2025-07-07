package com.api.pedidosApi.services;

import com.api.pedidosApi.DTO.ItemPedidoDTO;
import com.api.pedidosApi.Repositories.ItemPedidoRepository;
import com.api.pedidosApi.Repositories.PedidoRepository;
import com.api.pedidosApi.Repositories.ProdutoRepository;
import com.api.pedidosApi.models.ItemPedido;
import com.api.pedidosApi.models.Pedido;
import com.api.pedidosApi.models.Produto;
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

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public List<ItemPedido> findAll() {
        return itemPedidoRepository.findAll();
    }

    public ItemPedido findById(Integer id) {
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
        return itemPedido.orElseThrow(() -> new ObjectNotFoundException(
                "Item de Pedido não encontrado! Id: " + id + ", Tipo: " + ItemPedido.class.getName()));
    }

    public ItemPedido inserirItemPedido(ItemPedidoDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setDesconto(dto.getDesconto());
        item.setQuantidade(dto.getQuantidade());
        item.setPreco(dto.getPreco());

        return itemPedidoRepository.save(item);
    }
   /* public ItemPedido inserirItemPedido(ItemPedido ItemPedido){
        ItemPedido.setId(null);
        return itemPedidoRepository.save(ItemPedido);
    }*/

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
