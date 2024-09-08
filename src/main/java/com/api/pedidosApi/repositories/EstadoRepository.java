package com.api.pedidosApi.repositories;

import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
