package com.api.pedidosApi.Repositories;

import com.api.pedidosApi.models.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Integer> {
}
