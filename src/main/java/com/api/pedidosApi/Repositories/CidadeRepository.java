package com.api.pedidosApi.Repositories;

import com.api.pedidosApi.models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
