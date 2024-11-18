package com.api.pedidosApi.Repositories;


import com.api.pedidosApi.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    @Query("SELECT MAX(p.parcelaPagto) FROM Pagamento p WHERE p.idPedido = :idPedido")
    Integer findMaxParcelaPagtoByPedidoId(@Param("idPedido") Integer idPedido);

}
