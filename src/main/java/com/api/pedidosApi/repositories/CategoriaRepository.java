package com.api.pedidosApi.repositories;

import com.api.pedidosApi.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
