package com.api.pedidosApi.Repositories;


import com.api.pedidosApi.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
