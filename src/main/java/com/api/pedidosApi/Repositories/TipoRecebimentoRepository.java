package com.api.pedidosApi.Repositories;

import com.api.pedidosApi.models.TipoRecebimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRecebimentoRepository extends JpaRepository<TipoRecebimento, Integer> {
}