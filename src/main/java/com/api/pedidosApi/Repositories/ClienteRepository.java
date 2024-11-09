package com.api.pedidosApi.Repositories;

import com.api.pedidosApi.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}