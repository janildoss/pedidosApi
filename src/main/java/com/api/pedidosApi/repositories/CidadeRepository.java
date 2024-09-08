package com.api.pedidosApi.repositories;

import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
