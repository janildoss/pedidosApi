package com.api.pedidosApi.repositories;

import com.api.pedidosApi.models.Categoria;
import com.api.pedidosApi.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
