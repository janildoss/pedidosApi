package com.api.pedidosApi.Repositories;

import com.api.pedidosApi.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
