package com.api.pedidosApi.Repositories;


import com.api.pedidosApi.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    //@Query("SELECT MAX(p.parcelaPagto) FROM Pagamento p WHERE p.pedido.id = :pedidoId")
   // Integer findMaxParcelaPagtoByPedidoId(@Param("pedidoId") Integer pedidoId);

    //@Query("SELECT MAX(p.parcelaPagto) FROM Pagamento p WHERE p.id_Pedido = :id_Pedido")
  //Integer findMaxParcelaPagtoByPedidoId(@Param("id_Pedido") Integer id_Pedido);
}
